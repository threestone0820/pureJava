package three.stone.algorithm.two_heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array.
 * Answers within 10-5 of the actual value will be accepted.
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 */
public class SlidingWindowMedian {
    public static double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> result = new ArrayList<>();
        Comparator<int[]> comparator = Comparator.comparingInt((int[] a) -> a[1]).thenComparing(a -> a[0]);
        TreeSet<int[]> minHeap = new TreeSet<>(comparator);
        TreeSet<int[]> maxHeap = new TreeSet<>(comparator.reversed());

        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[] {i, nums[i]});
            minHeap.add(maxHeap.pollFirst());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.pollFirst());
            }
        }
        result.add(getMedian(maxHeap, minHeap));

        for (int i = k; i < nums.length; i++) {
            int[] removedValue = new int[]{i - k, nums[i - k]};
            int[] addedValue = new int[]{i, nums[i]};

            if (maxHeap.contains(removedValue)) {
                maxHeap.remove(removedValue);
            } else {
                minHeap.remove(removedValue);
                minHeap.add(maxHeap.pollFirst());
            }
            maxHeap.add(addedValue);
            minHeap.add(maxHeap.pollFirst());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.pollFirst());
            }
            result.add(getMedian(maxHeap, minHeap));
        }

        return result.stream().mapToDouble(d -> d).toArray();
    }

    private static double getMedian(TreeSet<int[]> maxHeap, TreeSet<int[]> minHeap) {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.first()[1];
        } else {
            Integer left = maxHeap.first()[1];
            Integer right = minHeap.first()[1];
            return ((double) left + (double)right) / 2;
        }
    }
}
