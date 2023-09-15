package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
public class _0023_Merge_K_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0), prev = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode polledNode = minHeap.poll();
            ListNode next = polledNode.next;
            polledNode.next = null;
            if (next != null) {
                minHeap.offer(next);
            }
            prev.next = polledNode;
            prev = polledNode;
        }
        return dummy.next;
    }
}
