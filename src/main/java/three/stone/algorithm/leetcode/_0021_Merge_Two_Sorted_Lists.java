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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null), p = list1, q = list2, prev = dummy;
        while (p != null || q != null) {
            if (p != null && q != null) {
                ListNode min;
                if (p.val < q.val) {
                    min = p;
                    p = p.next;
                } else {
                    min = q;
                    q = q.next;
                }
                prev.next = min;
                prev = prev.next;
                min.next = null;
            } else if (p != null) {
                prev.next = p;
                break;
            } else {
                prev.next = q;
                break;
            }
        }
        return dummy.next;
    }

}
