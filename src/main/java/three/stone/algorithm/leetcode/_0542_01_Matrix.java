package three.stone.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 */
public class _0542_01_Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visit = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    visit[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = cur[0] + dirs[i][0];
                int c = cur[1] + dirs[i][1];
                if (r >= 0 && r < m && c >= 0 && c < n && !visit[r][c]) {
                    visit[r][c] = true;
                    mat[r][c] = mat[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        return mat;
    }
}

