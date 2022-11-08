package three.stone.algorithm.in_place_reversal_linked_list;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (null == head || head.next == null) {
            return head;
        }

        int length = 0;
        ListNode sentinel = new ListNode(0, head);
        // 不要用sentinel作为指针往前移动，会导致循环链表
        // 重新定义一个指针p往前移动
        ListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        sentinel.next = head;

        k = k % length;
        if (k == 0) {
            return head;
        }

        ListNode quick = head, slow = head;
        while (k-- > 0) {
            quick = quick.next;
        }
        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }

        quick.next = sentinel.next;
        sentinel.next = slow.next;
        slow.next = null;

        return sentinel.next;
    }

}
