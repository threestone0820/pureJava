package three.stone.algorithm.leetcode;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * 1 <= left <= right <= n
 */
public class _0092_Reverse_Linked_List_II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head || null == head.next || left == right) {
            return head;
        }

        int gape = right - left;
        ListNode sentinel = new ListNode(0, head);
        ListNode prev = sentinel, slow = head,  quick = head;
        while (gape-- > 0) {
            quick = quick.next;
        }

        while (left-- > 1) {
            prev = prev.next;
            slow = slow.next;
            quick = quick.next;
        }

        prev.next = quick.next;
        quick.next = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev.next;
            prev.next = slow;
            slow = next;
        }
        return sentinel.next;
    }
}
