package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values.
 * If multiple values have the same frequency, sort them in decreasing order.
 */
public class _1636_SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, (oldValue, unused) -> oldValue + 1);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator
                        .comparingInt((int[] arr) -> arr[1])
                        .thenComparing(Comparator.comparingInt((int[] arr) -> arr[0]).reversed()));
        counter.forEach((k, v) -> minHeap.offer(new int[]{k, v}));
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            for (int i = 0; i < polled[1]; i++) {
                result.add(polled[0]);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
