package three.stone.algorithm.leetcode;

public class _0130_SurroundedRegions {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, visit, m, n, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, visit, m, n, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, visit, m, n, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, visit, m, n, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, boolean[][] visit, int m, int n, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || visit[i][j] || board[i][j] == 'X') {
            return;
        }
        visit[i][j] = true;
        board[i][j] = '*';
        dfs(board, visit, m, n, i + 1, j);
        dfs(board, visit, m, n, i - 1, j);
        dfs(board, visit, m, n, i, j + 1);
        dfs(board, visit, m, n, i, j - 1);
    }
}
