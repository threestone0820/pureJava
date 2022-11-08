package three.stone.algorithm.topk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k,
 * return the k most frequent elements. You may return the answer in any order.
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (old, unused) -> old + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (heap.size() < k) {
                heap.offer(entry);
            } else {
                Integer count = heap.peek().getValue();
                if (entry.getValue() > count) {
                    heap.poll();
                    heap.offer(entry);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll().getKey());
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
