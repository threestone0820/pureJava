package three.stone.algorithm.leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem
 * without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 */
public class _0024_Swap_Nodes_in_Pairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sentinel = new ListNode(0, head);
        ListNode p = sentinel;
        while (p.next != null && p.next.next != null) {
            ListNode temp = p.next;
            p.next = p.next.next;
            temp.next = p.next.next;
            p.next.next = temp;
            p = p.next.next;
        }

        return sentinel.next;
    }
}
