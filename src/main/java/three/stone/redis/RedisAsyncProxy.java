package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.RedisCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.base.AsyncUtils;
import three.stone.base.ExpiringHeap;
import three.stone.exception.WrapNonRuntimeException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisAsyncProxy<K, V> implements InvocationHandler, RedisCommandExecutor, RedisAsyncClientAdditionalApi<K, V> {
    private Logger logger = LogManager.getLogger();
    private static final int DEFAULT_REDIS_TIMEOUT = 1;

    private ConnectionManager<K, V> connectionManager;
    private RedisCommandRetryer redisCommandRetryer;
    private int timeout;
    private ExpiringHeap<ExpiringRedisCommand> PENDING_COMMANDS;


    public RedisAsyncProxy(String host, int port, RedisCodec<K, V> codec) {
        this(host, port, codec, DEFAULT_REDIS_TIMEOUT);
    }

    public RedisAsyncProxy(String host, int port, RedisCodec<K, V> codec, int timeout) {
        this.connectionManager = new ConnectionManager<>(host, port, codec);
        this.redisCommandRetryer = new RedisCommandRetryer(this);
        this.timeout = timeout;
        this.PENDING_COMMANDS = new ExpiringHeap<>(10L);
    }

    public static <K, V> RedisAsyncClientApi<K, V> create(String host, int port, RedisCodec<K, V> codec) {
        RedisAsyncProxy<K, V> redisAsyncProxy = new RedisAsyncProxy<>(host, port, codec);
        return createFromInvocationHandler(redisAsyncProxy, RedisAsyncClientApi.class);
    }

    public static <T> T createFromInvocationHandler(InvocationHandler proxy, Class<T> apiClass) {
        Object object = Proxy.newProxyInstance(RedisAsyncProxy.class.getClassLoader(),
                new Class[]{apiClass}, proxy);
        return apiClass.cast(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        logger.info("execute command method:{} args:{}", method, Arrays.toString(args));
        if (method.getDeclaringClass().isAssignableFrom(RedisAsyncCommands.class)) {
            return redisCommandRetryer.executeRedisCommandWithRetry(Collections.singletonList(new RedisCommand(method, args)));
        } else {
            return invokeMethod(this, method, args);
        }
    }

    @Override
    public CompletionStage<Object> executeCommands(List<RedisCommand> redisCommands) {
        if (redisCommands.size() != 1) {
            throw new IllegalStateException("More than one command to execute");
        }
        return connectionManager
                .getCommands()
                .thenCompose(client -> executeCommandWithTimeout(client, redisCommands.get(0)));
    }

    public CompletionStage<Object> executeCommandWithTimeout(RedisAsyncCommands<K, V> client, RedisCommand redisCommand) {
        CompletableFuture<Object> commandStage = executeCommand(client, redisCommand).toCompletableFuture();
        ExpiringRedisCommand pendingCommand = new ExpiringRedisCommand(System.currentTimeMillis() + timeout, commandStage);
        PENDING_COMMANDS.add(pendingCommand);
        return commandStage.whenComplete((r, e) -> PENDING_COMMANDS.expireById(pendingCommand.id()));
    }

    CompletionStage<Object> executeCommand(RedisAsyncCommands<K, V> client, RedisCommand redisCommand) {
        return (CompletionStage<Object>)invokeMethod(client, redisCommand.getMethod(), redisCommand.getArgs());
    }

    private Object invokeMethod(Object object, Method method, Object[] args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw WrapNonRuntimeException.wrap(e);
        }
    }

    @Override
    public void handleException(Throwable e) {
        connectionManager.reconnect();
    }

    @Override
    public RedisPipelineApi<K, V> pipeline() {
        return RedisPipelineProxy.create(this);
    }

    @Override
    public RedisTransactionApi<K, V> transaction() {
        return RedisTransactionProxy.create(this);
    }

    CompletionStage<RedisAsyncCommands<K, V>> getRedisCommands() {
        return connectionManager.getCommands();
    }

    CompletionStage<List<Object>> executeCommands(RedisAsyncCommands<K, V> redisAsyncCommands,
                                                  List<RedisCommand> commands) {
        List<CompletionStage<Object>> stages = new ArrayList<>();
        for (RedisCommand command : commands) {
            stages.add(executeCommand(redisAsyncCommands, command));
        }
        return AsyncUtils.join(stages);
    }
}
