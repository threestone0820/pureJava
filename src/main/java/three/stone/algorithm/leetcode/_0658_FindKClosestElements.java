package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a sorted integer array arr, two integers k and x,
 * return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 */
public class _0658_FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                Comparator
                        .comparingInt((int[] nums) -> -nums[1])
                        .thenComparingInt((int[] nums) -> -nums[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{num, Math.abs(x - num)});
                continue;
            }
            int[] peekNums = maxHeap.peek();
            int value1 = Math.abs(x - num);
            if (value1 < peekNums[1] || (value1 == peekNums[1] && num < peekNums[0])) {
                maxHeap.poll();
                maxHeap.offer(new int[]{num, value1});
            }
        }

        while (!maxHeap.isEmpty()) {
            minHeap.offer(maxHeap.poll()[0]);
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        return result;
    }
}
