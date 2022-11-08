package three.stone.algorithm.two_pointer;

import java.util.Arrays;

/**
 * Given an integer array nums, you need to find one continuous subarray that
 * if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 0
 */
public class Shortest_Unsorted_Continuous_Subarray {
    public int findUnsortedSubarray(int[] nums) {
        int low = 0, high = nums.length - 1;

        // 注意：第一轮找持续递增的下标时，用<=
        while (low < nums.length - 1 && nums[low] <= nums[low + 1]) {
            low++;
        }

        for (int i = low + 1; i < nums.length; i++) {
            // 第二轮找下标时，不能用 <=， 只能用 <，例如：
            /**
             * Input:
             * [1,3,2,3,3]
             * Output:
             * 4
             * Expected:
             * 2
             */
            if (nums[i] < nums[low]) {
                while (low >= 0 && nums[i] < nums[low]) {
                    low--;
                }
                if (low == -1) {
                    break;
                }
            }
        }

        while (high > 0 && nums[high] >= nums[high - 1]) {
            high--;
        }
        for (int i = high - 1; i >= 0; i--) {
            if (nums[i] > nums[high]) {
                while (high < nums.length && nums[i] > nums[high]) {
                    high++;
                }
                if (high == nums.length) {
                    break;
                }
            }
        }
        if (low == nums.length - 1 && high == 0) {
            return 0;
        }

        return high - low - 1;
    }

    public int findUnsortedSubarrayII(int[] nums) {
        int n = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);

        int start = 0;
        while (start < n  && nums[start] == temp[start]) start++;

        int end = n - 1;
        while (end > start  && nums[end] == temp[end]) end--;

        return end - start + 1;
    }
}
