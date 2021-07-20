package three.stone.basic.zk;


import org.junit.Test;
import three.stone.zk.StoneZookeeper;

import java.util.List;

public class StoneZookeeperTest {
    private static StoneZookeeper zk = StoneZookeeper.getInstance();

    @Test
    public void testZkGetEmptyChildren() {
        List<String> workersChildren = zk.getChildrenInfinitely("/workers");
        assert workersChildren.size() == 0;
    }

    @Test(expected=RuntimeException.class)
    public void testZkGetNotExistyChildren() {
        List<String> workersChildren = zk.getChildrenInfinitely("/notexist");
    }
}
