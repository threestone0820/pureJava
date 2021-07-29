package three.stone.algorithm;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Input: head = [1,2]
 * Output: [2,1]
 */
public class _0206_Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode sentinel = new ListNode(0, null);
        while (head != null) {
            ListNode next = head.next;
            head.next = sentinel.next;
            sentinel.next = head;
            head = next;
        }
        return sentinel.next;
    }
}
