package three.stone.algorithm.fast_slow_pointer;

import three.stone.algorithm.leetcode.ListNode;

/**
 * 1, 2, 3, 4, 5, 6, 7, 5
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 */
public class Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
        if (null == head || head.next == null) {
            return null;
        }

        // 找到相遇的node
        ListNode slow = head, quick = head;
        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
            if (quick == null || quick.next == null) {
                return null;
            }
            if (slow == quick) {
                break;
            }
        }

        // 相遇的node和head同时前进，比如回相遇在环开始的结点
        slow = head;
        while (slow != quick) {
            slow = slow.next;
            quick = quick.next;
        }
        return slow;
    }
}
