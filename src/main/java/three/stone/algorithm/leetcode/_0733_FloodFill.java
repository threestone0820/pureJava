package three.stone.algorithm.leetcode;

/**
 * Input: image =
 * [[1,1,1],
 *  [1,1,0],
 *  [1,0,1]], sr = 1, sc = 1, color = 2
 * Output:
 * [[2,2,2],
 *  [2,2,0],
 *  [2,0,1]]
 */
public class _0733_FloodFill {
    private int m;
    private int n;
    boolean[][] visit;
    int[][] image;
    int color;
    int oldColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.m = image.length;
        this.n = image[0].length;
        this.visit = new boolean[m][n];
        this.image = image;
        this.color = color;
        this.oldColor = image[sr][sc];
        dfs(sr, sc);
        return this.image;
    }

    private void dfs(int i, int j) {
        if (i >= 0 && i < m && j >= 0 && j < n &&
                !visit[i][j] && image[i][j] == oldColor) {
            image[i][j] = color;
            visit[i][j] = true;
            dfs(i - 1, j);
            dfs(i + 1, j);
            dfs(i , j - 1);
            dfs(i , j + 1);
        }
    }
}
