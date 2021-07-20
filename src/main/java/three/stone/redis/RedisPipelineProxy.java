package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisPipelineProxy<K, V> extends RedisAsyncBaseCommandsGroupProxy<K, V> {
    private Logger logger = LogManager.getLogger();

    public static <K, V> RedisPipelineApi<K, V> create(RedisAsyncProxy<K, V> baseProxy) {
        return RedisAsyncProxy.createFromInvocationHandler(new RedisPipelineProxy<>(baseProxy),
                RedisPipelineApi.class);
    }

    public RedisPipelineProxy(RedisAsyncProxy<K, V> baseProxy) {
        super(baseProxy);
    }

    @Override
    protected CompletionStage<?> executeCommands(RedisAsyncCommands<K, V> redisAsyncCommands, List<RedisCommand> commands) {
        if (commands.isEmpty()) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        redisAsyncCommands.setAutoFlushCommands(false);
        CompletionStage<List<Object>> stages = baseProxy.executeCommands(redisAsyncCommands, commands);
        redisAsyncCommands.flushCommands();
        redisAsyncCommands.setAutoFlushCommands(true);
        return stages;
    }
}
