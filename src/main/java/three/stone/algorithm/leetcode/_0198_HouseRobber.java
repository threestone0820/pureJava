package three.stone.algorithm.leetcode;

public class _0198_HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = nums[i];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i - 1][0] + nums[i];
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
