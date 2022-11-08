package three.stone.algorithm.in_place_reversal_linked_list;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */
public class ReverseSubLinkedList {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head || null == head.next || left == right) {
            return head;
        }

        ListNode sentinel = new ListNode(0, head);
        ListNode slow = sentinel, quick = sentinel, cur = sentinel;
        for (int i = 1; i <= right; i++) {
            // slow是left位置前的那个节点
            if (i == left) {
                slow = cur;
            }
            cur = cur.next;
            // quick就是right位置所指的结点
            if (i == right) {
                quick = cur;
            }
        }

        // 翻转slow 至 quick： 注意先把链子断开了
        cur = slow.next;
        slow.next = quick.next;
        quick.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = slow.next;
            slow.next = cur;
            cur = next;
        }

        return sentinel.next;
    }
}
