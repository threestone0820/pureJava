package three.stone.redis;

import io.lettuce.core.RedisCommandInterruptedException;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.protocol.AsyncCommand;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RedisFutureHelper<T> extends CompletableFuture<T> implements RedisFuture<T> {
    @Override
    public String getError() {
        return "Unknown Redis error";
    }

    /**
     * NOTE: the implementation is copied from {@link AsyncCommand}
     */
    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            get(timeout, unit);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RedisCommandInterruptedException(e);
        } catch (ExecutionException e) {
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
