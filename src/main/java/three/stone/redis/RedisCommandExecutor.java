package three.stone.redis;

import io.lettuce.core.RedisFuture;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

public interface RedisCommandExecutor {
    CompletionStage<Object> executeCommand(RedisCommand redisCommand);

    void handleException(Throwable e);
}
