package three.stone.algorithm.leetcode;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray
 * of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 *
 * Output: 2
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class _0209_Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE, start = 0, end = 0, sum = 0;
        while (end < nums.length) {
            sum += nums[end++];
            while (sum >= target) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
