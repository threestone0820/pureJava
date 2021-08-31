package three.stone.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer.
 *
 * Notice that you should not modify the linked list.
 */
public class _0142_Linked_List_CycleII {
    /**
     * 思路1：
     *
     * Assume the distance from head to the start of the loop is x1
     * the distance from the start of the loop to the point fast and slow meet is x2
     * the distance from the point fast and slow meet to the start of the loop is x3
     * What is the distance fast moved? What is the distance slow moved? And their relationship?
     *
     * x1 + x2 + x3 + x2
     * x1 + x2
     * x1 + x2 + x3 + x2 = 2 (x1 + x2)
     * Thus x1 = x3
     */
    public ListNode detectCycle(ListNode head) {
        if (null == head || head.next == null) {
            return null;
        }

        ListNode slow = head, quick = head;
        boolean flag = false;
        while (quick != null) {
            slow = slow.next;
            if (quick.next == null) {
                return null;
            }
            quick = quick.next.next;
            if (slow == quick) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            return null;
        }

        slow = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode detectCycleII(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, quick = head;
        boolean flag = false;
        while (quick != null) {
            if (quick.next == null) {
                return null;
            }
            slow = slow.next;
            quick = quick.next.next;
            if (slow == quick) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            return null;
        }

        // 用set记住圆圈内的结点，时间复杂度比方法一慢点...
        Set<ListNode> set = new HashSet<>();
        do {
            set.add(slow);
            slow = slow.next;
        } while (slow != quick);

        slow = head;
        while (!set.contains(slow)) {
            slow = slow.next;
        }
        return slow;
    }
}
