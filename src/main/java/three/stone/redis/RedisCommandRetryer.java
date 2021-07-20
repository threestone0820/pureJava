package three.stone.redis;

import io.lettuce.core.RedisFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.base.VarHolder;
import three.stone.exception.Exceptions;
import three.stone.exception.WrapNonRuntimeException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisCommandRetryer {
    Logger logger = LogManager.getLogger();
    private RedisCommandExecutor executor;

    public RedisCommandRetryer(RedisCommandExecutor executor) {
        this.executor = executor;
    }

    public RedisFuture<Object> executeRedisCommandWithRetry(List<RedisCommand> redisCommands) {
        RedisFutureHelper<Object> future = new RedisFutureHelper<>();
        CompletionStage<Object> stage = (CompletionStage<Object>) executor.executeCommands(redisCommands);

        VarHolder<Throwable> exceptionHolder = new VarHolder<>();
        stage.thenApply(result -> result)
                .exceptionally(throwable -> {
                    exceptionHolder.setValue(throwable);
                    return null;
                })
                .thenCompose(result -> {
                    if (exceptionHolder.getValue() == null) {
                        return CompletableFuture.completedFuture(result);
                    } else {
                        Throwable t = WrapNonRuntimeException.unWrap(exceptionHolder.getValue());
                        handleException(t, 1);
                        return (CompletionStage<Object>) executor.executeCommands(redisCommands);
                    }
                })
                .whenComplete((result, t) -> {
                    if (null == t) {
                        future.complete(result);
                    } else {
                        Throwable unWrapped = WrapNonRuntimeException.unWrap(t);
                        handleException(unWrapped, 2);
                        future.completeExceptionally(unWrapped);
                    }
                });

        return future;
    }

    private void handleException(Throwable e, int times) {
        logger.warn("Got exception when execute redis command , e:{} times:{}",
                Exceptions.toString(e), times);
        executor.handleException(e);
    }
}
