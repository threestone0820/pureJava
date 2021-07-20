package three.stone.redis;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface RedisCommandExecutor {
    CompletionStage<?> executeCommands(List<RedisCommand> redisCommand);

    void handleException(Throwable e);
}
