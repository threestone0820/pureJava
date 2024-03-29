package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity.
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 */
public class _0215_KthLargestElementInArray {

    public int findKthLargestII(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                minHeap.offer(nums[i]);
            } else {
                if (nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
        }
        return minHeap.poll();
    }


    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int target = nums.length - k;
        while (low <= high) {
            int index = partition(nums, low, high);
            if (index == target) {
                break;
            } else if (index < target) {
                low = index + 1;
            } else {
                high = index - 1;
            }
        }
        return nums[target];
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high];
        int j = low;
        for (int i = low; i < high; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, j, high);
        return j;
    }

    private void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
