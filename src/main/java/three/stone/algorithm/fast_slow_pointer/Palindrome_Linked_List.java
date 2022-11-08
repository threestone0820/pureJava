package three.stone.algorithm.fast_slow_pointer;

import three.stone.algorithm.leetcode.ListNode;

/**
 * 1 2 1
 * 1 2 2 1
 * 1 2 3 4 3 2 1
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
public class Palindrome_Linked_List {
    public boolean isPalindrome(ListNode head) {
        // 注意：找到的slow 的位置是中间位置（奇数）或中间位置的的下一个位置（偶数）
        ListNode slow = head, quick = head;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode reversedHead = reverseList(slow);
        ListNode reversedHeadCopy = reversedHead;
        while (head != null && reversedHead != null) {
            if (head.val != reversedHead.val) {
                return false;
            }
            head = head.next;
            reversedHead = reversedHead.next;
        }

        reverseList(reversedHeadCopy);
        // 此时一定为true了
        return true;
    }

    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0, null);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }
}
