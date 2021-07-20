package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.RedisCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.base.ExpiringHeap;
import three.stone.exception.WrapNonRuntimeException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AsyncRedisProxy<K, V> implements InvocationHandler, RedisCommandExecutor, RedisAsyncClientAdditionalApi<K, V> {
    private Logger logger = LogManager.getLogger();
    private static final int DEFAULT_REDIS_TIMEOUT = 1;

    private ConnectionManager<K, V> connectionManager;
    private RedisCommandRetryer redisCommandRetryer;
    private int timeout;
    private ExpiringHeap<ExpiringRedisCommand> PENDING_COMMANDS;


    public AsyncRedisProxy(String host, int port, RedisCodec<K, V> codec) {
        this(host, port, codec, DEFAULT_REDIS_TIMEOUT);
    }

    public AsyncRedisProxy(String host, int port, RedisCodec<K, V> codec, int timeout) {
        this.connectionManager = new ConnectionManager<>(host, port, codec);
        this.redisCommandRetryer = new RedisCommandRetryer(this);
        this.timeout = timeout;
        this.PENDING_COMMANDS = new ExpiringHeap<>(10L);
    }

    public static <K, V> RedisAsyncClientApi<K, V> create(String host, int port, RedisCodec<K, V> codec) {
        AsyncRedisProxy<K, V> asyncRedisProxy = new AsyncRedisProxy<>(host, port, codec);
        Object object = Proxy.newProxyInstance(
                AsyncRedisProxy.class.getClassLoader(),
                new Class[]{RedisAsyncClientApi.class},
                asyncRedisProxy);

        return (RedisAsyncClientApi<K, V>) object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        logger.info("execute command method:{} args:{}", method, Arrays.toString(args));
        if (method.getDeclaringClass().isAssignableFrom(RedisAsyncCommands.class)) {
            return redisCommandRetryer.executeRedisCommandWithRetry(new RedisCommand(method, args));
        } else {
            return invokeMethod(this, method, args);
        }
    }

    @Override
    public CompletionStage<Object> executeCommand(RedisCommand redisCommand) {
        logger.info("execute command method:{} args:{}",
                redisCommand.getMethod(), Arrays.toString(redisCommand.getArgs()));
        return connectionManager
                .getCommands()
                .thenCompose(client -> executeCommandWithTimeout(client, redisCommand));
    }

    private CompletionStage<Object> executeCommandWithTimeout(RedisAsyncCommands<K, V> client, RedisCommand redisCommand) {
        CompletableFuture<Object> commandStage = executeCommand(client, redisCommand).toCompletableFuture();
        ExpiringRedisCommand pendingCommand = new ExpiringRedisCommand(System.currentTimeMillis() + timeout, commandStage);
        PENDING_COMMANDS.add(pendingCommand);
        return commandStage.whenComplete((r, e) -> PENDING_COMMANDS.expireById(pendingCommand.id()));
    }

    private CompletionStage<Object> executeCommand(RedisAsyncCommands<K, V> client, RedisCommand redisCommand) {
        return invokeMethod(client, redisCommand.getMethod(), redisCommand.getArgs());
    }

    private CompletionStage<Object> invokeMethod(Object object, Method method, Object[] args) {
        try {
            return (CompletionStage<Object>) method.invoke(object, args);
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
        return (RedisPipelineApi<K, V>) Proxy.newProxyInstance(AsyncRedisProxy.class.getClassLoader(),
                new Class[]{RedisPipelineApi.class}, new RedisPipelineProxy(this));
    }

    CompletionStage<RedisAsyncCommands<K, V>> getRedisCommands() {
        return connectionManager.getCommands();
    }
}
