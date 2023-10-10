package three.stone.algorithm.leetcode;

public class _0935_KnightDialer {
    public int knightDialer(int n) {
        int mod = 1_000_000_000 + 7;
        int result = 0;
        int[][][] dp = new int[4][3][n + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result = (result + helper(n, 1, i, j, dp)) % mod;
            }
        }
        result = (result + helper(n, 1, 3, 1, dp)) % mod;
        return result;
    }

    private int helper(int n, int move, int row, int column, int[][][] dp) {
        if (row < 0 || column < 0 || row >= 4 || column >= 3 ||
                (row == 3 && (column == 0 || column == 2))) {
            return 0;
        }
        if (move == n) {
            return 1;
        }
        int mod = 1_000_000_000 + 7;
        if (dp[row][column][move] != 0) {
            return dp[row][column][move] % mod;
        }
        int result = 0;
        result = (result + helper(n, move + 1, row - 2, column - 1, dp)) % mod;
        result = (result + helper(n, move + 1, row - 2, column + 1, dp)) % mod;
        result = (result + helper(n, move + 1, row + 2, column - 1, dp)) % mod;
        result = (result + helper(n, move + 1, row + 2, column + 1, dp)) % mod;
        result = (result + helper(n, move + 1, row - 1, column - 2, dp)) % mod;
        result = (result + helper(n, move + 1, row + 1, column - 2, dp)) % mod;
        result = (result + helper(n, move + 1, row - 1, column + 2, dp)) % mod;
        result = (result + helper(n, move + 1, row + 1, column + 2, dp)) % mod;
        dp[row][column][move] = result;
        return result;
    }
}
