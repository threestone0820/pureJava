package three.stone.algorithm.leetcode;

/**
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Input: l1 = [1,2,4], l2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 */
public class _0021_Merge_Two_Sorted_Lists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            ListNode next;
            if (l1.val < l2.val) {
                next = l1;
                l1 = l1.next;
            } else {
                next = l2;
                l2 = l2.next;
            }
            next.next = null;
            p.next = next;
            p = next;
        }
        p.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

}
