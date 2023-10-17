package three.stone.algorithm.leetcode;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Input: head = []
 * Output: []
 */
public class _0148_Sort_List {
    public ListNode sortListII(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return mergeSort(head, tail);
    }

    private ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }
        ListNode slow = head, quick = head;
        while (quick != tail && quick.next != tail) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode node = slow.next;
        slow.next = null;
        ListNode p = mergeSort(head, slow);
        ListNode q = mergeSort(node, tail);
        return merge(p, q);
    }

    private ListNode merge(ListNode p, ListNode q) {
        ListNode sentinel = new ListNode(), prev = sentinel;
        while (p != null && q != null) {
            if (p.val < q.val) {
                prev.next = p;
                p = p.next;
            } else {
                prev.next = q;
                q = q.next;
            }
            prev = prev.next;
        }
        if (p != null) {
            prev.next = p;
        }
        if (q != null) {
            prev.next = q;
        }
        return sentinel.next;
    }

    public ListNode sortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode sentinel = new ListNode(0, head);
        quickSort(sentinel, null);
        return sentinel.next;
    }

    private ListNode partition(ListNode start, ListNode end) {
        ListNode current = start.next, p = start.next.next;
        current.next = end;
        while (p != end) {
            ListNode next = p.next;
            if (p.val < current.val) {
                p.next = start.next;
                start.next = p;
            } else {
                p.next = current.next;
                current.next = p;
            }
            p = next;
        }
        return current;
    }

    private void quickSort(ListNode start, ListNode end) {
        if (start.next == end || start.next.next == end) {
            return;
        }

        ListNode mid = partition(start, end);
        quickSort(start, mid);
        quickSort(mid, end);
    }
}
