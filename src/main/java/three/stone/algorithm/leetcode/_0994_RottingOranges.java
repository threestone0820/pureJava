package three.stone.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 */
public class _0994_RottingOranges {
    // leetcode中的解法挺有意思：
    // 把腐烂的橘子放到queue里面，同时累加所有新鲜橘子的个数
    // 当bfs时如果要把橘子变为腐烂的，则新鲜橘子的个数减一
    // 最后根据新鲜橘子个数是否为0来判断
    // 注意最后return时要-1，因为while循环多累加了一次...
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (allRotted(grid, m, n)) {
            return 0;
        }
        boolean[][] visit = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    visit[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int minute = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            minute++;
            for (int j = 0; j < size; j++) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int r = dirs[i][0] + cur[0];
                    int c = dirs[i][1] + cur[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && !visit[r][c] && grid[r][c] == 1) {
                        visit[r][c] = true;
                        grid[r][c] = 2;
                        queue.offer(new int[]{r, c});
                    }
                }
            }

        }
        if (allRotted(grid, m, n)) {
            return minute - 1;
        }

        return -1;
    }

    private boolean allRotted(int[][] grid, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
