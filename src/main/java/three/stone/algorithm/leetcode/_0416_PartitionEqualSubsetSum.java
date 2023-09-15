package three.stone.algorithm.leetcode;

/**
 * Given an integer array nums, return true if you can partition the array
 * into two subsets such that the sum of the elements in both subsets is equal
 * or false otherwise.
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class _0416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            if (num > sum) {
                continue;
            }

            for (int i = sum; i >= sum; i--) {
                if (i + num > sum) {
                    continue;
                }
                if (dp[i]) {
                    dp[i + num] = true;
                }
            }
            dp[num] = true;
            if (dp[sum]) {
                return true;
            }
        }
        return false;
     }
}
