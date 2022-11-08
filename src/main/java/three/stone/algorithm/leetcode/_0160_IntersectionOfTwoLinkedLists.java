package three.stone.algorithm.leetcode;

/**
 * Given the heads of two singly linked-lists headA and headB,
 * return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 */
public class _0160_IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa.next == null ? headB : pa.next;
            pb = pb.next == null ? headA : pb.next;
        }
        return null;
    }
}
