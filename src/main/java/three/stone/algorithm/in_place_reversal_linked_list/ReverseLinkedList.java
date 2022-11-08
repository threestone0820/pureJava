package three.stone.algorithm.in_place_reversal_linked_list;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode sentinel = new ListNode(0, null);
        while (head != null) {
            ListNode next = head.next;
            head.next = sentinel.next;
            sentinel.next = head;
            head = next;
        }
        return sentinel.next;
    }
}
