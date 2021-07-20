package three.stone.redis;

import three.stone.base.EventLoop;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ConnectionFuture;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisConnectionException;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.resource.ClientResources;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ConnectionManager<K, V> implements Closeable {
    private static final ClientResources CLIENT_RESOURCE =
            ClientResources
                    .builder()
                    .eventLoopGroupProvider(new RedisEventLoopGroupProvider())
                    .eventExecutorGroup(EventLoop.getEventLoopGroup())
                    .build();

    private static final ClientOptions CLIENT_OPTIONS =
            ClientOptions
                    .builder()
                    .autoReconnect(false)
                    .cancelCommandsOnReconnectFailure(true)
                    .socketOptions(
                            SocketOptions
                                    .builder()
                                    .connectTimeout(Duration.ofSeconds(5))
                                    .build())
                    .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                    .build();

    private String host;
    private int port;
    private RedisURI redisURI;
    private RedisClient client;
    private RedisCodec<K, V> codec;
    private ConnectionFuture<StatefulRedisConnection<K, V>> connectionFuture;
    private StatefulRedisConnection<K, V> connection;

    public ConnectionManager(String host, int port, RedisCodec<K, V> codec) {
        this.host = host;
        this.port = port;
        this.codec = codec;
        initClient();
    }

    private void initClient() {
        redisURI = RedisURI.Builder.redis(host, port).build();
        client = RedisClient.create(CLIENT_RESOURCE, redisURI);
        client.setOptions(CLIENT_OPTIONS);
    }

    public CompletionStage<RedisAsyncCommands<K, V>> getCommands() {
        if (connection != null) {
            return CompletableFuture.completedFuture(connection.async());
        }

        if (connectionFuture == null) {
            reconnect();
        }

        return connectionFuture.thenApply(StatefulRedisConnection::async);
    }

    public void reconnect() {
        if (connectionFuture != null && !connectionFuture.isDone()) {
            return;
        }

        if (connectionFuture != null && connectionFuture.isDone()) {
            closeConnection();
        }

        connect();
    }

    private void closeConnection() {
        if (null != connection && connection.isOpen()) {
            connection.close();
        }
        connection = null;
        connectionFuture = null;
        client.shutdownAsync()
                .exceptionally(e -> {
                    System.out.println("close client e " + e);
                    return null;
                });
        client = null;
        redisURI = null;
    }

    private void forceCloseConnection() {
        if (null == connectionFuture) {
            return;
        }

        if (!connectionFuture.isDone()) {
            connectionFuture.cancel(true);
        }
        closeConnection();
    }

    private void connect() {
        System.out.println("begin connect");
        initClient();
        connectionFuture = client
                .connectAsync(codec, redisURI)
                .thenApply(connection -> {
                    System.out.println("success to connect to three.stone.redis " + redisURI.getHost() + ":" + redisURI.getPort());
                    this.connection = connection;
                    return connection;
                })
                .exceptionally(e -> {
                    System.out.println("Fail to connect to three.stone.redis " + redisURI.getHost() + ":" + redisURI.getPort() + ":" + e);
                    throw new RedisConnectionException(redisURI.getHost(), e);
                });
    }

    @Override
    public void close() throws IOException {
        forceCloseConnection();
    }
}
