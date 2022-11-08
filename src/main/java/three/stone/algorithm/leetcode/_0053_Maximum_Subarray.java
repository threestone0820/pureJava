package three.stone.algorithm.leetcode;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.*
 * Input: nums = [1]
 * Output: 1
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
public class _0053_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE, curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            curMax = Math.max(curMax + nums[i], nums[i]);
            result = Math.max(result, curMax);
        }
        return result;
    }
}
