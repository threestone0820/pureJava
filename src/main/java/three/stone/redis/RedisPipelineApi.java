package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface RedisPipelineApi<K, V> extends RedisAsyncCommands<K, V> {
    CompletionStage<List<Object>> execute();
}
