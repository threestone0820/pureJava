package three.stone.algorithm;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 */
public class _0061_Rotate_List {
    public ListNode rotateRight(ListNode head, int k) {
        int length = 0;
        ListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        if (length < 2) {
            return head;
        }
        k = k % length;
        if (k == 0) {
            return head;
        }

        ListNode slow = head, quick = head;
        while (k-- > 0) {
            quick = quick.next;
        }

        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next;
        }

        quick.next = head;
        head = slow.next;
        slow.next = null;

        return head;
    }
}
