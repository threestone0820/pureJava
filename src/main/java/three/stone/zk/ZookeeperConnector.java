package three.stone.zk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ZookeeperConnector {
    private static final Logger logger = LogManager.getLogger();
    private static final String ZOOKEEPER_HOST = "zookeeper:2181,zookeeper:2182,zookeeper:2183";
    private static final int SESSION_TIMEOUT = 10 * 1000;
    private static ZooKeeper zk = null;

    public ZooKeeper getZk() {
        if (null == zk) {
            connect();
        } else if (!zk.getState().isAlive()) {
            close();
            zk = null;
            connect();
        }

        return zk;
    }

    public ZooKeeper getZkInfinitely() {
        while (true) {
            ZooKeeper zk = getZk();
            if (null != zk) {
                return zk;
            } else {
                logger.error("Failed to get zk, sleep 1 second.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("Failed to sleep. :{}", e.toString());
                }
            }
        }
    }

    private void connect() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        try {
            zk = new ZooKeeper(ZOOKEEPER_HOST, SESSION_TIMEOUT, event -> {
                logger.info("WatchedEvent: {}", event);
                future.complete(null);
            });
        } catch (IOException e) {
            logger.error("IOException when connect, e:{}", e.toString());
            zk = null;
            future.complete(null);
        }

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            logger.error("failed to close zk e:{}", e.toString());
        }
    }

}
