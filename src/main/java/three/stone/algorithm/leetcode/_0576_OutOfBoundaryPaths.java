package three.stone.algorithm.leetcode;

public class _0576_OutOfBoundaryPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        return helper(m, n, maxMove, startRow, startColumn, dp);
    }

    private int helper(int m, int n, int maxMove, int startRow, int startColumn, int[][][] dp) {
        if (startRow < 0 || startRow >= m || startColumn < 0 || startColumn >= n) {
            return 1;
        }
        if (maxMove == 0) {
            return 0;
        }
        if (dp[startRow][startColumn][maxMove] != 0) {
            return dp[startRow][startColumn][maxMove];
        }

        int mod = 1000_000_007;
        int result = 0;
        result = (result + helper(m, n, maxMove - 1, startRow - 1, startColumn, dp)) % mod;
        result = (result + helper(m, n, maxMove - 1, startRow + 1, startColumn, dp)) % mod;
        result = (result + helper(m, n, maxMove - 1, startRow, startColumn - 1, dp)) % mod;
        result = (result + helper(m, n, maxMove - 1, startRow, startColumn + 1, dp)) % mod;
        dp[startRow][startColumn][maxMove] = result;
        return result;
    }
}
