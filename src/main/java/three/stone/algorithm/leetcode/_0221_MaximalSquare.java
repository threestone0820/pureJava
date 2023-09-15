package three.stone.algorithm.leetcode;

/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class _0221_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, column = matrix[0].length;
        int result = 0;
        int[][] dp = new int[row + 1][column + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result * result;
    }
}
