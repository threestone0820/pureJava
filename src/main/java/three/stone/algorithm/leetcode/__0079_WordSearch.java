package three.stone.algorithm.leetcode;

public class __0079_WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return false;
        }
        char ch = word.charAt(index);
        if (board[i][j] != ch) {
            return false;
        }
        board[i][j] = '#';
        if (dfs(board, i + 1, j, word, index + 1) || dfs(board, i - 1, j, word, index + 1) ||
                dfs(board, i, j - 1, word, index + 1) || dfs(board, i, j + 1, word, index + 1)) {
            return true;
        }
        board[i][j] = ch;
        return false;
    }
}
