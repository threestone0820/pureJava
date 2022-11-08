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
    Map<Integer, Node<Integer, Integer>> map;
    Node<Integer, Integer> head;
    Node<Integer, Integer> tail;

    public _0146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node<>(0, 0);
        this.tail = new Node<>(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node<Integer, Integer> node = map.get(key);
            visitNode(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node<Integer, Integer> node = map.get(key);
        if (node != null) {
            node.value = value;
            visitNode(node);
        } else {
            if (map.size() >= capacity) {
                Node<Integer, Integer> movedNode = tail.prev;
                movedNode.prev.next = tail;
                tail.prev = movedNode.prev;
                map.remove(movedNode.key);
                movedNode.prev = null;
                movedNode.next = null;
            }

            node = new Node<>(key, value);
            map.put(key, node);
            headInsert(node);
        }
    }

    private void visitNode(Node<Integer, Integer> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        headInsert(node);
    }

    private void headInsert(Node<Integer, Integer> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        _0146_LRUCache lruCache = new _0146_LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(1);
    }

}
