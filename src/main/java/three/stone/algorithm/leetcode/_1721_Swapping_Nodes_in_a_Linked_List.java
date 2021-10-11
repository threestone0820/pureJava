package three.stone.algorithm.leetcode;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from
 * the beginning and the kth node from the end (the list is 1-indexed).
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * Example 3:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 * Example 4:
 *
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 * Example 5:
 *
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
 */
public class _1721_Swapping_Nodes_in_a_Linked_List {
    // 思路：这两个节点距离链表的头、尾的距离是相同的
    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = head, second = head;
        while (--k > 0) {
            first = first.next;
        }

        ListNode p = first;
        int remain = 0;
        while (p.next != null) {
            remain++;
            p = p.next;
        }
        while (remain-- > 0) {
            second = second.next;
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return head;
    }
}
