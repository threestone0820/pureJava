package three.stone.algorithm.leetcode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes
 * less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
public class _0086_Partition_List {
    public ListNode partition(ListNode head, int x) {
        if (null == head || head.next == null) {
            return head;
        }

        // 创建一个值为x的结点作为枢纽
        ListNode current = new ListNode(x, null);
        ListNode sentinel = new ListNode(0, current);
        ListNode p = sentinel, q = current;

        while (head != null) {
            ListNode next = head.next;
            if (head.val < x) {
                head.next = p.next;
                p.next = head;
                p = p.next;
            } else {
                head.next = q.next;
                q.next = head;
                q = q.next;
            }
            head = next;
        }
        //删除创建的那个冗余结点
        p.next = p.next.next;

        return sentinel.next;
    }
}
