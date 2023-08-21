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
        ListNode dummy = new ListNode(0, head), prev = dummy, p = head, q = head;
        while (--left > 0) {
            prev = prev.next;
            p = p.next;
        }
        while (--right > 0) {
            q = q.next;
        }
        prev.next = q.next;
        q.next = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = prev.next;
            prev.next = p;
            p = next;
        }
        return dummy.next;
    }
}
