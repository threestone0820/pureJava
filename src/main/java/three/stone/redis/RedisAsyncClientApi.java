package three.stone.redis;

import io.lettuce.core.api.async.RedisAsyncCommands;

public interface RedisAsyncClientApi<K, V> extends RedisAsyncCommands<K, V>, RedisAsyncClientAdditionalApi<K, V> {
}
