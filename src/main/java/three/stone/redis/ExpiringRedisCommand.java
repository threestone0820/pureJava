package three.stone.redis;

import io.lettuce.core.RedisException;
import three.stone.base.ExpiringItem;

import java.util.concurrent.CompletableFuture;

public class ExpiringRedisCommand implements ExpiringItem {
    private static long increasing_id = 1;

    private long id;
    private long expiration;
    private int position;
    private CompletableFuture<Object> commandStage;

    public ExpiringRedisCommand(long expiration, CompletableFuture<Object> commandStage) {
        this.id = increasing_id++;
        this.expiration = expiration;
        this.commandStage = commandStage;
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public long expiration() {
        return this.expiration;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void expired() {
        commandStage.completeExceptionally(new RedisException("timeout"));
    }
}
