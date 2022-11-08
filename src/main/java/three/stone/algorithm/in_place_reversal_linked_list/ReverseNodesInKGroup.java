package three.stone.algorithm.in_place_reversal_linked_list;

import three.stone.algorithm.leetcode.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesInKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode prev = new ListNode(0, head);
        ListNode p = prev, q = prev;
        while (true) {
            int steps = k;
            while (q != null && steps-- > 0) {
                q = q.next;
            }
            if (q == null) {
                break;
            }

            // 注意，颠倒位置前，先记住第一个结点
            //（调用reverse方法后，它会变成最后一个结点，也就是下一个K组的前驱结点）
            ListNode nextPrev = p.next;
            reverse(p, q);
            p = q = nextPrev;
        }

        return prev.next;
    }

    private static void reverse(ListNode p, ListNode q) {
        ListNode cur = p.next;
        // 必须用下面两行代码，先把链子先断开，然后用头插法颠倒位置
        // 否则指针会混乱
        p.next = q.next;
        q.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = p.next;
            p.next = cur;
            cur = next;
        }
    }
}
