package three.stone.algorithm.fast_slow_pointer;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 */
public class Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if (null == head || head.next == null) {
            return false;
        }

        ListNode slow = head, quick = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
            if (slow == quick) {
                return true;
            }
        }

        return false;
    }
}
