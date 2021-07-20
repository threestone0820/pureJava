package three.stone.redis;

public interface RedisAsyncClientAdditionalApi<K, V> {
    RedisPipelineApi<K, V> pipeline();

    RedisTransactionApi<K, V> transaction();
}
