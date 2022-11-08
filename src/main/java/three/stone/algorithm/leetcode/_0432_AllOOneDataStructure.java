package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure to store the strings' count with the ability
 * to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1.
 * If key does not exist in the data structure, insert it with count 1.
 *
 * dec(String key) Decrements the count of the string key by 1.
 * If the count of key is 0 after the decrement, remove it from the data structure.
 * It is guaranteed that key exists in the data structure before the decrement.
 *
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 *
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
public class _0432_AllOOneDataStructure {
    private Map<String, Node> map;
    private Node head;
    private Node tail;

    private static class Node{
        String key;
        int count;
        Node prev;
        Node next;

        public Node(String key, int count) {
            this.key = key;
            this.count = count;
        }
    }
    public _0432_AllOOneDataStructure() {
        map = new HashMap<>();
        head = new Node(null, -1);
        tail = new Node(null, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, 1);
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            map.put(key, node);
        } else {
            node.count = node.count + 1;
            sortAfterInc(node);
        }
    }

    private void sortAfterInc(Node node) {
        Node p = node.next;
        while (p != tail && p.count < node.count) {
            p = p.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = p;
        node.prev = p.prev;
        p.prev.next = node;
        p.prev = node;
    }

    public void dec(String key) {
        Node node = map.get(key);
        node.count = node.count - 1;
        if (node.count == 0) {
            removeNode(key, node);
        } else {
            sortAfterDec(node);
        }
    }

    private void removeNode(String key, Node node) {
        map.remove(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void sortAfterDec(Node node) {
        Node p = node.prev;
        while (p != head && p.count > node.count) {
            p = p.prev;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = p.next;
        node.prev = p;
        p.next.prev = node;
        p.next = node;
    }

    public String getMaxKey() {
        if (tail.prev != head) {
            return tail.prev.key;
        }
        return "";
    }

    public String getMinKey() {
        if (head.next != tail) {
            return head.next.key;
        }
        return "";
    }
}
