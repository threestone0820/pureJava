package three.stone.algorithm;

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
            return head;
        }
        // 用map记录了每个node的位置，为了正确的赋值random指针，新的链表也要从前往后找到对应的位置
        Map<Node, Integer> map = new HashMap<>();
        Node p = head;
        int index = 0;
        while (p != null) {
            map.put(p, index++);
            p = p.next;
        }

        p = head;
        Node sentinel = new Node(0);
        Node q = sentinel;
        while (p != null) {
            q.next = new Node(p.val);
            q = q.next;
            p = p.next;
        }

        p = head;
        q = sentinel.next;
        while (p != null) {
            if (p.random != null) {
                index = map.get(p.random);
                Node r = sentinel.next;
                while (index > 0) {
                    r = r.next;
                    index--;
                }
                q.random = r;
            }
            p = p.next;
            q = q.next;
        }

        return sentinel.next;
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
