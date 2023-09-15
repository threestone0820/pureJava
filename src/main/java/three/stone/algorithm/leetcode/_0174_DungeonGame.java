package three.stone.algorithm.leetcode;

/**
 *
 */
public class _0174_DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length, column = dungeon[0].length;
        int[][] dp = new int[row + 1][column + 1];
        for (int i = 0; i < row + 1; i++) {
            dp[i][column] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < column + 1; i++) {
            dp[row][i] = Integer.MAX_VALUE;
        }

        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0 ; j--) {
                int need = (i == row - 1 && j == column - 1) ? 1 : Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = need - dungeon[i][j] <= 0 ? 1 : need - dungeon[i][j];
            }
        }

        return dp[0][0];
    }
}
