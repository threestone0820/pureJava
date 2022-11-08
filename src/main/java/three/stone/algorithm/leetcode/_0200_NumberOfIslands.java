package three.stone.algorithm.leetcode;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class _0200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit =new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visit[i][j]) {
                    result++;
                    dfs(grid, visit, m, n, i, j);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, boolean[][] visit, int m, int n, int i, int j) {
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1' && !visit[i][j]) {
            visit[i][j] = true;
            dfs(grid, visit, m, n, i - 1, j);
            dfs(grid, visit, m, n, i + 1, j);
            dfs(grid, visit, m, n, i, j - 1);
            dfs(grid, visit, m, n, i, j + 1);
        }
    }
}
