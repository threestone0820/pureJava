package three.stone.algorithm;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 */
public class _0053_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        /**
         * So I change the format of the sub problem into something like: maxSubArray(int A[], int i),
         * which means the maxSubArray for A[0:i ] which must has A[i] as the end element.
         * Note that now the sub problem's format is less flexible and less powerful than the previous one
         * because there's a limitation that A[i] should be contained in that sequence
         * and we have to keep track of each solution of the sub problem to update the global optimal value.
         * However, now the connect between the sub problem & the original one becomes clearer:
         *
         * maxSubArray(A, i) = (maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0) + A[i];
         *
         * 注意：这不是细化problem，而是细化solution，solution必须以 i 作为子串的结尾
         */
        int length = nums.length;
        //其实不用数组，只要记住dp[i - 1]就行了
        int[] dp = new int[length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
