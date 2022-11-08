package three.stone.algorithm.topk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (old, unused) -> old + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]).thenComparing(a -> -a[0]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] result = new int[nums.length];
        int index = 0;
        while (!heap.isEmpty()) {
            int[] element = heap.poll();
            for (int i = 0; i < element[1]; i++) {
                result[index++] = element[0];
            }
        }
        return result;
    }
}
