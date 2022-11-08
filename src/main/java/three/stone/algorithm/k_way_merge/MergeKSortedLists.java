package three.stone.algorithm.k_way_merge;

import three.stone.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists,
 * each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * Example 1:
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
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0, null);
        ListNode cur = head;
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode list : lists) {
            if (null != list) {
                heap.offer(list);
            }
        }

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                heap.offer(node.next);
            }
            node.next = null;
        }
        return head.next;
    }
}
