package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _0373_FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0] + arr[1]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            result.add(Arrays.asList(polled[0], polled[1]));
            if (polled[2] == nums2.length - 1) {
                continue;
            }
            minHeap.offer(new int[] {polled[0], nums2[polled[2] + 1], polled[2] + 1});
        }
        return result;
    }
}
