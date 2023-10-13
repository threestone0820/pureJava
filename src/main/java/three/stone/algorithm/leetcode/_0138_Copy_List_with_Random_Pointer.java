package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value
 * set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes
 * in the copied list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 */
public class _0138_Copy_List_with_Random_Pointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Integer> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        Node dummy = new Node(0), p = head, q = dummy;
        Integer index = 0;
        while (p != null) {
            Node copiedNode = new Node(p.val);
            map1.put(p, index);
            map2.put(index, copiedNode);
            index++;

            q.next = copiedNode;
            q = q.next;
            p = p.next;
        }

        p = head;
        q = dummy.next;
        while (p != null) {
            if (p.random == null) {
                q.random = null;
            } else {
                q.random = map2.get(map1.get(p.random));
            }
            p = p.next;
            q = q.next;
        }
        return dummy.next;
    }

    public Node copyRandomListII(Node head) {
        if (null == head) {
            return null;
        }

        /**
         * 牛逼的方式:
         * 1、把原链表扩大成两倍长度
         * 2、很方便的赋值random指针
         * 3、把链表拆成两个链表，复原以前的，返回copy的
         */
        Node p = head;
        while (p != null) {
            Node copy = new Node(p.val);
            copy.next = p.next;
            p.next = copy;
            p = copy.next;
        }

        p = head;
        while (p != null) {
            if (null != p.random) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        p = head;
        Node newHead = p.next, q = newHead;
        while (p != null) {
            p.next = p.next.next;
            // 小心：p.next肯定不为空，p结点的后面是copy的结点，但q.next可能为null
            if (q.next != null) {
                q.next = q.next.next;
            }
            p = p.next;
            q = q.next;
        }

        return newHead;
    }
}
