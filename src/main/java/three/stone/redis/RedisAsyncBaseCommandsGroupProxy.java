package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

abstract class RedisAsyncBaseCommandsGroupProxy<K, V> implements InvocationHandler, RedisCommandExecutor{
    protected RedisAsyncProxy<K, V> baseProxy;
    protected RedisCommandRetryer retryer;
    protected List<RedisCommand> commands;

    public RedisAsyncBaseCommandsGroupProxy(RedisAsyncProxy<K, V> baseProxy) {
        this.baseProxy = baseProxy;
        this.commands = new ArrayList<>();
        this.retryer = new RedisCommandRetryer(this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().isAssignableFrom(RedisAsyncCommands.class)) {
            commands.add(new RedisCommand(method, args));
            return null;
        }

        return retryer.executeRedisCommandWithRetry(commands);
    }

    @Override
    public CompletionStage<?> executeCommands(List<RedisCommand> redisCommand) {
        return baseProxy.getRedisCommands()
                .thenCompose(redisCommands -> {
                    return executeCommands(redisCommands, commands);
                });
    }

    protected abstract CompletionStage<?> executeCommands(RedisAsyncCommands<K, V> redisAsyncCommands,
                                                          List<RedisCommand> commands);


    @Override
    public void handleException(Throwable e) {
        this.baseProxy.handleException(e);
    }
}
