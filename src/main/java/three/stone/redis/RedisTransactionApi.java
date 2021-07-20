package three.stone.redis;

import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.CompletionStage;

public interface RedisTransactionApi<K, V> extends RedisAsyncCommands<K, V> {
    CompletionStage<TransactionResult> execute();
}
