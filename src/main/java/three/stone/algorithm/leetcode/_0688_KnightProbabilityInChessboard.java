package three.stone.algorithm.leetcode;

public class _0688_KnightProbabilityInChessboard {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k + 1];
        return helper(n, k, row, column, dp);
    }

    private double helper(int n, int k, int row, int column, double[][][] dp) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (dp[row][column][k] != 0) {
            return dp[row][column][k];
        }

        double result = ((double) 1 / 8 * helper(n, k - 1, row - 2, column - 1, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row - 2, column + 1, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row + 2, column - 1, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row + 2, column + 1, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row - 1, column - 2, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row + 1, column - 2, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row - 1, column + 2, dp)) +
                ((double) 1 / 8 * helper(n, k - 1, row + 1, column + 2, dp));
        dp[row][column][k] = result;
        return result;
    }
}
