package three.stone.algorithm.fast_slow_pointer;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 * 1, 3, 5
 * 1, 2, 3, 4
 */
public class Middle_of_the_Linked_List {
    public ListNode middleNode(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode slow = head, quick = head;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }
}
