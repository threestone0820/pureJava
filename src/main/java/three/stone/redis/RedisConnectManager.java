package three.stone.redis;

import io.lettuce.core.ConnectionFuture;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.RedisCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import three.stone.base.ServiceInfo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RedisConnectManager<K, V> implements AutoCloseable{
    private Logger logger = LogManager.getLogger();

    private ServiceInfo serviceInfo;
    private RedisURI redisURI;
    private RedisClient redisClient;
    private RedisCodec<K, V> codec;
    private StatefulRedisConnection<K, V> connection;
    private ConnectionFuture<StatefulRedisConnection<K, V>> connectFuture;
    private CompletionStage<Void> closeFuture;

    public RedisConnectManager(ServiceInfo serviceInfo, RedisCodec<K, V> codec) {
        this.codec = codec;
        this.redisURI = RedisURI.Builder.redis(serviceInfo.getHost(), serviceInfo.getPort()).build();
        this.redisClient = RedisClient.create(redisURI);
    }

    public CompletionStage<RedisAsyncCommands<K, V>> getRedisCommands() {
        if (null != connection) {
            return CompletableFuture.completedFuture(connection.async());
        }

        if (connectFuture == null) {

        }

        return connectFuture.thenApply(connection -> {
            this.connection = connection;
            return connection.async();
        });
    }

    void reconnect() {
        if (connectInProgress()) {
            logger.info("Reconnecting to {}.{}", serviceInfo.getHost(), serviceInfo.getPort());
            return;
        }

        if (connectAttemptFinished()) {

        }
    }

    private boolean connectInProgress() {
        return connectFuture != null && !connectFuture.isDone();
    }

    private boolean connectAttemptFinished() {
        return connectFuture != null && connectFuture.isDone();
    }

    private void closeConnect() {
        if (connection == null) {
            return;
        }

        if (closeFuture != null && closeFuture.toCompletableFuture().isDone())

        closeFuture = connection.closeAsync()
                .exceptionally(e -> {
                    logger.error("Failed to close redis connection, {}.{}", serviceInfo.getHost(), serviceInfo.getPort());
                    connection = null;
                    return null;
                })
                .thenAccept(unused -> {
                    logger.info("Close connection to {}.{} ", serviceInfo.getHost(), serviceInfo.getPort());
                    connection = null;
                });
    }

    @Override
    public void close() throws Exception {

    }
}
