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
        if (null == head || head.next == null) {
            return head;
        }

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        //三指针移动
        ListNode tail = sentinel, p = sentinel, cur = head, q = head.next;
        while (cur != null) {
            if ((p == sentinel || p.val != cur.val) &&
                    (q == null || q.val != cur.val)) {
                tail.next = cur;
                tail = cur;
            }
            p = p.next;
            cur = cur.next;
            if (q != null) {
                q = q.next;
            }
        }
        tail.next = null;

        return sentinel.next;
    }
}
