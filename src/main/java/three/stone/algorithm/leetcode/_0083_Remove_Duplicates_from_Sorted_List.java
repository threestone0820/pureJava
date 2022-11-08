package three.stone.algorithm.leetcode;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
public class _0083_Remove_Duplicates_from_Sorted_List {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode p = head, q = head.next;
        while (q != null) {
            if (q.val != p.val) {
                p.next = q;
                p = p.next;
            }
            q = q.next;
        }
        p.next = null;

        return head;
    }
}
