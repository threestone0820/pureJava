package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 */
public class _0347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, (oldValue, unused) -> oldValue + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (heap.size() < k) {
                heap.offer(new int[]{key, value});
                continue;
            }
            if (value > heap.peek()[1]) {
                heap.poll();
                heap.offer(new int[]{key, value});
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll()[0];
        }
        return result;
    }
}
