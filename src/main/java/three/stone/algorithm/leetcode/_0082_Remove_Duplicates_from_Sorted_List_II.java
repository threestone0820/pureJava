package three.stone.algorithm.leetcode;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class _0082_Remove_Duplicates_from_Sorted_List_II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0, head), prev = head;
        while (head != null) {
            if (head.next == null || head.next.val != head.val) {
                head = head.next;
                prev = prev.next;
            } else {
                int value = head.val;
                while (head != null && head.val == value) {
                    head = head.next;
                }
                if (head != null) {
                    head = head.next;
                    prev.next = head;
                }
            }
        }
        return sentinel.next;
    }
}
