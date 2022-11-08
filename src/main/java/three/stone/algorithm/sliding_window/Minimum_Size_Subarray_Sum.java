package three.stone.algorithm.sliding_window;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
 * of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 */
public class Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int target, int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int start = 0, end = 0, currentSum = 0, result = Integer.MAX_VALUE;
        while (end < nums.length) {
            currentSum += nums[end++];
            while (currentSum >= target) {
                result = Math.min(result, end - start);
                currentSum -= nums[start++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
