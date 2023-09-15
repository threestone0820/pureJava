package three.stone.algorithm.leetcode;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row
 * that is either directly below or diagonally left/right. Specifically, the next element
 * from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class _0931_MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                } else if (j == n - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1])) + matrix[i][j];
                }
            }
        }

        int result = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }
}
