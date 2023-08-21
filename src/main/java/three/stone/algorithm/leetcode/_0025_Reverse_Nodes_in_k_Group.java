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
        ListNode prev = sentinel, q = kthNode(prev, k);
        while (q != null) {
            ListNode nextPrev = prev.next;
            ListNode p = prev.next;
            prev.next = q.next;
            q.next = null;
            while (p != null) {
                ListNode next = p.next;
                p.next = prev.next;
                prev.next = p;
                p = next;
            }
            q = kthNode(nextPrev, k);
            prev = nextPrev;
        }
        return sentinel.next;
    }

    private static ListNode kthNode(ListNode node, int k) {
        while (node != null && k-- > 0) {
            node = node.next;
        }
        return node;
    }
}
