package three.stone.algorithm.leetcode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the node
 * that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
public class _0141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, quick = slow.next;
        while (quick != null) {
            if (slow == quick) {
                return true;
            }
            slow = slow.next;
            if (quick.next == null) {
                return false;
            } else {
                quick = quick.next.next;
            }
        }
        return false;
    }
}
