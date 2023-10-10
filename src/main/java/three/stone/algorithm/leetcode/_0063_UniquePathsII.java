package three.stone.algorithm.leetcode;

public class _0063_UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        int[] dp = new int[c];
        for (int i = 0; i < c; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[i] = 1;
            } else {
                while (i < c) {
                    dp[i] = 0;
                    i++;
                }
                break;
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] = j == 0 ? dp[j] : dp[j] + dp[j - 1];
                }
            }
        }
        return dp[c - 1];
    }
}
