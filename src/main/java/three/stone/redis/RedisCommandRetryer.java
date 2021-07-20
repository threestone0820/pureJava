package three.stone.redis;

import io.lettuce.core.RedisFuture;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.exception.Exceptions;
import three.stone.exception.WrapNonRuntimeException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RedisCommandRetryer {
    Logger logger = LogManager.getLogger();
    private RedisCommandExecutor executor;

    public RedisCommandRetryer(RedisCommandExecutor executor) {
        this.executor = executor;
    }

    public RedisFuture<Object> executeRedisCommandWithRetry(RedisCommand redisCommand) {
        RedisFutureHelper<Object> future = new RedisFutureHelper<>();

        executor.executeCommand(redisCommand)
                .thenApply(result -> Pair.of(Optional.of(result), Optional.empty()))
                .exceptionally(e -> Pair.of(Optional.empty(), Optional.of(e)))
                .thenCompose(pair -> {
                    if (pair.getLeft().isPresent()) {
                        return CompletableFuture.completedFuture(pair.getLeft().get());
                    } else {
                        Throwable e = WrapNonRuntimeException.unWrap((Throwable) pair.getRight().get());
                        handleException(e, 1);
                        return executor.executeCommand(redisCommand);
                    }
                })
                .whenComplete((result, e) -> {
                    if (null == e) {
                        future.complete(result);
                    } else {
                        Throwable unWrapped = WrapNonRuntimeException.unWrap(e);
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
