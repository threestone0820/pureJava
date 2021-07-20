package three.stone.redis;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import three.stone.base.AsyncUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisPipelineProxy<K, V> implements InvocationHandler {
    private List<RedisCommand> commands;
    private AsyncRedisProxy<K, V> baseProxy;

    public RedisPipelineProxy(AsyncRedisProxy<K, V> baseProxy) {
        this.commands = new ArrayList<>();
        this.baseProxy = baseProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        checkCommand(method);
        if (method.getDeclaringClass().isAssignableFrom(RedisAsyncCommands.class)) {
            commands.add(new RedisCommand(method, args));
        }

        return executeCommands();
    }

    public CompletionStage<List<Object>> executeCommands() {
        if (commands.isEmpty()) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        return baseProxy.getRedisCommands()
                .thenCompose(redisCommands -> {
                    redisCommands.setAutoFlushCommands(false);
                    List<CompletionStage<Object>> stages = new ArrayList<>();
                    commands.stream()
                            .forEach(command -> stages.add(baseProxy.executeCommand(command)));
                    redisCommands.flushCommands();
                    redisCommands.setAutoFlushCommands(true);
                    return AsyncUtils.join(stages);
                });
    }

    private void checkCommand(Method method) {
        Class<?> clazz = method.getDeclaringClass();
        if (!(clazz.isAssignableFrom(RedisPipelineApi.class))) {
            throw new RuntimeException("illegal command, " + method.getName());
        }
    }


}
