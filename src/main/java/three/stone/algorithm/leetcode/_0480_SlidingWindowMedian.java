package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 *
 * You are given an integer array nums and an integer k.
 * There is a sliding window of size k which is moving from the very left of the array
 * to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array.
 * Answers within 10-5 of the actual value will be accepted.
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 */
public class _0480_SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        Comparator<int[]> comparator = Comparator.comparingInt((int[] arr) -> arr[1]).thenComparing(arr -> arr[0]);
        TreeSet<int[]> maxHeap = new TreeSet<>(comparator.reversed());
        TreeSet<int[]> minHeap = new TreeSet<>(comparator);
        for (int i = 0; i < k; i++) {
            int[] added = {i, nums[i]};
            maxHeap.add(added);
            minHeap.add(maxHeap.pollFirst());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.pollFirst());
            }
        }
        result[0] = getMedium(maxHeap, minHeap);

        for (int i = k; i < nums.length; i++) {
            int[] added = {i, nums[i]};
            int[] removed = {i - k, nums[i - k]};
            if (maxHeap.remove(removed)) {
                maxHeap.add(added);
            } else {
                minHeap.remove(removed);
                minHeap.add(added);
            }
            minHeap.add(maxHeap.pollFirst());
            maxHeap.add(minHeap.pollFirst());
            result[i - k + 1] = getMedium(maxHeap, minHeap);
        }
        return result;
    }

    private double getMedium(TreeSet<int[]> maxHeap, TreeSet<int[]> minHeap) {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.first()[1];
        } else {
            return ((double) maxHeap.first()[1] + (double) minHeap.first()[1]) / 2;
        }
    }
}
