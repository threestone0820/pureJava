package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c
 * or a < c if b - a == d - c.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 *
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 */
public class _0632_SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> -arr[0]));
        for (int i = 0; i < nums.size(); i++) {
            int[] arr = new int[] {nums.get(i).get(0), i, 0};
            minHeap.add(arr);
            maxHeap.add(arr);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                Comparator.comparingInt((int[] arr) -> arr[1] - arr[0])
                        .thenComparingInt((int[] arr) -> arr[0])
        );
        while (!minHeap.isEmpty()) {
            int[] minValue = minHeap.poll();
            int[] maxValue = maxHeap.peek();
            heap.offer(new int[]{minValue[0], maxValue[0]});
            List<Integer> list = nums.get(minValue[1]);
            if (minValue[2] == list.size() - 1) {
                break;
            } else {
                int[] arr = new int[]{list.get(minValue[2] + 1), minValue[1], minValue[2] + 1};
                minHeap.offer(arr);
                maxHeap.offer(arr);
            }
        }

        return heap.poll();
    }
}
