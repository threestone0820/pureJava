package three.stone.algorithm.leetcode;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * The number of nodes in the list is in the range [1, 100].
 */
public class _0876_MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            q = q.next.next;
            p = p.next;
        }
        return p;
    }
}
