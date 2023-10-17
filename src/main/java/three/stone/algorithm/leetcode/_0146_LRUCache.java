package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 */
public class _0146_LRUCache {
    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public _0146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            visitNode(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            visitNode(node);
        } else {
            node = new Node(key, value);
            map.put(key, node);
            insertHead(node);
            if (map.size() > capacity) {
                Node removed = tail.prev;
                removed.prev.next = tail;
                tail.prev = removed.prev;
                removed.prev = null;
                removed.next = null;
                map.remove(removed.key);
            }
        }
    }

    private void visitNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        insertHead(node);
    }

    private void insertHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    private static class Node {
        Integer key;
        Integer value;
        Node next;
        Node prev;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }


}
