package three.stone.algorithm.leetcode;

/**
 * Given an integer array nums, find a contiguous non-empty subarray
 * within the array that has the largest product, and return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 *
 * Input: nums = [-2,0,-1]  -2 1 -1
 * Output: 0
 */
public class _0152_MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int result = nums[0], curMax = nums[0], curMin = nums[0];
        for (int i = 1; i < len; i++) {
            int max = Math.max(nums[i], Math.max(nums[i] * curMin, nums[i] * curMax));
            int min = Math.min(nums[i], Math.min(nums[i] * curMin, nums[i] * curMax));
            curMax = max;
            curMax = min;
            result = Math.max(result, curMax);
        }
        return result;
    }

}
