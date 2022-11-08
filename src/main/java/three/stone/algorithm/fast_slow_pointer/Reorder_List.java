package three.stone.algorithm.fast_slow_pointer;

import three.stone.algorithm.leetcode.ListNode;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * 1 2 3 4 5
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class Reorder_List {
    public void reorderList(ListNode head) {
        ListNode slow = head, quick = head;
        // 注意：找到的slow 的位置是中间位置（奇数）或中间位置的的上一个位置（偶数）
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode halfHead = slow.next;
        slow.next = null;
        ListNode reversedHead = reverseList(halfHead);
        while (reversedHead != null) {
            ListNode next = reversedHead.next;
            reversedHead.next = head.next;
            head.next = reversedHead;
            reversedHead = next;
            head = head.next.next;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0, null);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }
}
