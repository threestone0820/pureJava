package three.stone.algorithm.leetcode;

/**
 * Note: You can only move either down or right at any point in time.
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 */
public class _0064_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[] dp = new int[column];
        dp[0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            dp[i] = grid[0][i] + dp[i - 1];
        }

        for (int i = 1; i < row; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < column; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[column - 1];
    }
}
