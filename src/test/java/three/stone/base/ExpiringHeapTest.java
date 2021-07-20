package three.stone.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpiringHeapTest {
    @Test
    public void testBasicHeap() {
        List<ExpiringHashItemInstance> expired = new ArrayList<>();
        ExpiringHashItemInstance.setExpiredContainer(expired);

        ExpiringHeap<ExpiringHashItemInstance> heap = new ExpiringHeap<>(-1);
        assertEquals(0, heap.size());

        heap.add(new ExpiringHashItemInstance(2, 2));
        assertEquals(1, heap.size());

        assertEquals(0, heap.expireItems(0));
        assertEquals(0, expired.size());

        assertEquals(1, heap.expireItems(2));
        assertEquals(0, heap.size());
        assertEquals(1, expired.size());
        assertEquals(2, expired.get(0).id());
        assertEquals(2, expired.get(0).expiration());
        expired.clear();

        heap.add(new ExpiringHashItemInstance(1, 1));
        assertEquals(1, heap.size());
        heap.add(new ExpiringHashItemInstance(3, 3));
        assertEquals(2, heap.size());

        assertEquals(0, heap.expireItems(0));
        assertEquals(0, expired.size());

        assertEquals(1, heap.expireItems(1));
        assertEquals(1, heap.size());
        assertEquals(1, expired.size());
        assertEquals(1, expired.get(0).id());
        assertEquals(1, expired.get(0).expiration());
        expired.clear();

        assertEquals(0, heap.expireItems(2));
        assertEquals(1, heap.size());
        assertEquals(0, expired.size());

        assertEquals(1, heap.expireItems(3));
        assertEquals(0, heap.size());
        assertEquals(1, expired.size());
        assertEquals(3, expired.get(0).id());
        assertEquals(3, expired.get(0).expiration());
    }

    @Test
    public void testRemovingById() {

    }

}

class ExpiringHashItemInstance implements ExpiringItem {

    private long id;
    private long expiration;
    private int heapPos;

    private static List<ExpiringHashItemInstance> expired = null;

    static void setExpiredContainer(List<ExpiringHashItemInstance> expired) {
        ExpiringHashItemInstance.expired = expired;
    }

    ExpiringHashItemInstance(long id, long expiration) {
        this.id = id;
        this.expiration = expiration;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public long expiration() {
        return expiration;
    }

    @Override
    public void setPosition (int pos) {
        this.heapPos = pos;
    }

    @Override
    public int getPosition() {
        return this.heapPos;
    }

    @Override
    public void expired() {
        if (expired != null) {
            expired.add(this);
        }
    }
}


