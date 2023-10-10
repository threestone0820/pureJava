package three.stone.algorithm.leetcode;

/**
 *
 * 0, 1, 2, 3, 4
 */
public class _1039_MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] values) {
        return helper(values, 0, values.length - 1, new int[51][51]);
    }

    private int helper(int[] values, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        for (int k = i + 1; k < j; k++) {
            dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                    helper(values, i, k, dp) + helper(values, k, j, dp) + values[i] * values[j] * values[k]);
        }
        return dp[i][j];
    }

    public int minScoreTriangulationII(int[] values) {
        int n = values.length;
        int[][] dp = new int[51][51];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                            dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
