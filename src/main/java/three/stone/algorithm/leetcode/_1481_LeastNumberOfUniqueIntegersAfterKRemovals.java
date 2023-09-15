package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 *
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 */
public class _1481_LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : arr) {
            counter.merge(num, 1, (old, unused) -> old + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        counter.forEach((key, v) -> minHeap.offer(v));
        while (k > 0) {
            if (k > minHeap.peek()) {
                k -= minHeap.poll();
            } else {
                break;
            }
        }
        return minHeap.size();
    }
}
