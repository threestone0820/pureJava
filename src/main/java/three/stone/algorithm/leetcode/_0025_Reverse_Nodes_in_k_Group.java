package three.stone.algorithm.leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 */
public class _0025_Reverse_Nodes_in_k_Group {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode sentinel = new ListNode(0, head);
        ListNode prev = sentinel, end = head;
        while (end != null) {
            for (int i = 1; i < k; i++) {
                if (end.next != null) {
                    end = end.next;
                } else {
                    return sentinel.next;
                }
            }

            ListNode start = prev.next;
            prev.next = end.next;
            ListNode cur = start;

            // 先把next赋空，方便下面的循环判断
            end.next = null;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
                cur = next;
            }
            prev = start;
            end = start.next;
        }

        return sentinel.next;
    }
}
