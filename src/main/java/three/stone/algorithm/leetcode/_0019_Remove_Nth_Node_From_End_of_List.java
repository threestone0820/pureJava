package three.stone.algorithm.leetcode;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * The number of nodes in the list is sz.
 * 1 <= n <= sz
 */
public class _0019_Remove_Nth_Node_From_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode quick = dummy, slow = dummy;
        while (n-- > 0) {
            quick = quick.next;
        }
        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

