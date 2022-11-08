package three.stone.algorithm.leetcode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes
 * less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
public class _0086_Partition_List {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy1 = new ListNode(0, null);
        ListNode dummy2 = new ListNode(0, null);
        ListNode prev1 = dummy1, prev2 = dummy2;
        while (head != null) {
            ListNode tmp = head.val < x ? prev1 : prev2;
            tmp.next = head;
            head = head.next;
            tmp.next.next = null;
            if (tmp == prev1) {
                prev1 = prev1.next;
            } else {
                prev2 = prev2.next;
            }
        }
        prev1.next = dummy2.next;
        return dummy1.next;
    }
}
