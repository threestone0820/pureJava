package three.stone.zk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class StoneZookeeper {
    private static final Logger logger = LogManager.getLogger();

    private static final StoneZookeeper instance = new StoneZookeeper();

    private StoneZookeeper() {

    }

    public static StoneZookeeper getInstance() {
        return instance;
    }

    private ZookeeperConnector connector = new ZookeeperConnector();

    public boolean exist(String path) {
        for (int i = 0; i < 2; i++) {
            try {
                ZooKeeper zk = connector.getZk();
                if (null == zk) {
                    continue;
                }
                return zk.exists(path, false) != null;
            } catch (InterruptedException | KeeperException e) {
                logger.error("Failed to check path exist, e:{} path:{}", e.toString(), path);
            }
        }
        return false;
    }

    public List<String> getChildrenInfinitely(String path) {
        ZooKeeper zk = connector.getZkInfinitely();
        try {
            List<String> children = zk.getChildren(path, false);
            logger.info("children:{}", children);
            return children;
        } catch (KeeperException | InterruptedException e) {
            logger.error("Failed to get children, e:{} path:{}", e.toString(), path);
            throw new RuntimeException(e.toString());
        }
    }

    public byte[] get(String path) {
        ZooKeeper zk = connector.getZk();
        if (zk == null) {
            logger.error("zk connect returned null");
            return null;
        }
        try {
            return zk.getData(path, false, null);
        } catch (KeeperException | InterruptedException e) {
            zk = connector.getZk();
            if (zk == null) {
                logger.error("zk connect returned null (second time)");
                return null;
            }
            try {
                return zk.getData(path, false, null);
            } catch (KeeperException | InterruptedException e2) {
                logger.error("get failed twice: {}", e2.toString());
                return null;
            }
        }
    }

    public void getAsync(String path, AsyncCallback.DataCallback dataCallback) {
        ZooKeeper zk = connector.getZk();
        if (zk == null) {
            logger.error("zk connect returned null");
            dataCallback.processResult(KeeperException.Code.SYSTEMERROR.intValue(),
                    null, null, null, null);
            return;
        }
        try {
            zk.getData(path, false, dataCallback, null);
        } catch (Exception ex) {
            logger.error("Error fetching:{}", ex.toString());
            dataCallback.processResult(KeeperException.Code.SYSTEMERROR.intValue(),
                    null, null, null, null);
        }
    }


}
