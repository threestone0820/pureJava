package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisTransactionProxy<K, V> extends RedisAsyncBaseCommandsGroupProxy<K, V> {


    static <K, V> RedisTransactionApi<K, V> create(RedisAsyncProxy<K, V> baseProxy) {
        return RedisAsyncProxy.createFromInvocationHandler(new RedisTransactionProxy<>(baseProxy), RedisTransactionApi.class);
    }
    public RedisTransactionProxy(RedisAsyncProxy<K, V> baseProxy) {
        super(baseProxy);
    }

    @Override
    protected CompletionStage<?> executeCommands(RedisAsyncCommands<K, V> redisAsyncCommands, List<RedisCommand> commands) {
        if (commands.size() == 0) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        redisAsyncCommands.multi();
        baseProxy.executeCommands(redisAsyncCommands, commands);
        return redisAsyncCommands.exec();
    }
}
