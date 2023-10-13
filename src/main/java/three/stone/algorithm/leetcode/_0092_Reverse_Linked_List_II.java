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
        ListNode dummy = new ListNode(0, head), leftPrev = dummy, rightNode = dummy;
        while (left-- > 1) {
            leftPrev = leftPrev.next;
        }
        while (right-- > 0) {
            rightNode = rightNode.next;
        }
        ListNode node = leftPrev.next;
        leftPrev.next = rightNode.next;
        rightNode.next = null;
        while (node != null) {
            ListNode temp = node.next;
            node.next = leftPrev.next;
            leftPrev.next = node;
            node = temp;
        }
        return dummy.next;
    }
}
