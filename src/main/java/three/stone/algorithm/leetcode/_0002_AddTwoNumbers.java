package three.stone.algorithm.leetcode;

public class _0002_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, dummy = new ListNode(0, null), pre = dummy;
        int sum = 0;
        while (p != null || q != null) {
            if (p != null) {
                sum += p.val;
                p = p.next;
            }
            if (q != null) {
                sum += q.val;
                q = q.next;
            }
            pre.next = new ListNode(sum % 10, null);
            pre = pre.next;
            sum /= 10;
        }
        if (sum == 1) {
            pre.next = new ListNode(1, null);
        }
        return dummy.next;
    }
}
