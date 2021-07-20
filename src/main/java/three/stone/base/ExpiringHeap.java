package three.stone.base;

import io.netty.util.concurrent.ScheduledFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpiringHeap<V extends ExpiringItem> {
    private ArrayList<V> heap = new ArrayList<>();
    private Map<Long, V> map = new HashMap<>();
    private ScheduledFuture<?> scheduledFuture;

    public ExpiringHeap(long duration) {
        if (duration > 0) {
            scheduledFuture = EventLoop.getEventLoopGroup()
                    .scheduleAtFixedRate(() -> expireItems(System.currentTimeMillis()),
                            duration, duration, TimeUnit.MILLISECONDS);
        }
    }

    public void expireById(long id) {
        V removed = map.remove(id);
        if (null == removed) {
            return;
        }

        int position = removed.getPosition();
        V moved = heap.remove(heap.size() - 1);
        if (heap.size() > 0) {
            if (removed.expiration() < moved.expiration()) {
                moveDown(moved, position);
            } else {
                moveUp(moved, position);
            }
        }
    }

    public int expireItems(long expiration) {
        int expired = 0;
        while (heap.size() > 0 && heap.get(0).expiration() <= expiration) {
            V removed = heap.get(0);
            map.remove(removed.id());
            removed.expired();

            V moved = heap.remove(heap.size() - 1);
            if (heap.size() > 0) {
                moveDown(moved, 0);
            }
            expired++;
        }
        return expired;
    }

    public void add(V item) {
        heap.add(null);
        moveUp(item, heap.size() - 1);
        map.put(item.id(), item);
    }

    private int moveUp(V item, int position) {
        while (position > 0) {
            int parentPosition = (position - 1) >> 2;
            if (heap.get(parentPosition).expiration() > item.expiration()) {
                move(heap.get(parentPosition), position);
                position = parentPosition;
            } else {
                break;
            }
        }

        move(item, position);
        return position;
    }

    private void moveDown(V item, int position) {
        while (position < heap.size()) {
            int child = position * 2 + 1;
            if (child >= heap.size()) {
                break;
            }

            if (child + 1 < heap.size() && heap.get(child + 1).expiration() < heap.get(child).expiration()) {
                child = child + 1;
            }

            if (item.expiration() <= heap.get(child).expiration()) {
                break;
            }

            move(heap.get(child), position);
            position = child;
        }

        move(item, position);
    }

    private void move(V item, int position) {
        heap.set(position, item);
        item.setPosition(position);
    }

    public int size() {
        if (heap.size() != map.size()) {
            throw new RuntimeException("bug");
        }
        return heap.size();
    }
}
