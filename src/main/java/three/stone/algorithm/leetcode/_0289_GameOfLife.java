package three.stone.algorithm.leetcode;

public class _0289_GameOfLife {
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = numberOfOne(board, i, j);
                if (board[i][j] == 0) {
                    result[i][j] = num == 3 ? 1 : 0;
                } else {
                    result[i][j] = (num == 2 || num == 3) ? 1 : 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    private static int numberOfOne(int[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;

        int result = 0;
        for (int x = i - 1; x <= i + 1 ; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x == i && y == j) {
                    continue;
                }
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 1) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(arr);
    }
}
