package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _0212_WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            buildTrie(root, word);
        }

        Set<String> found = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrace(board, i, j, found, root);
            }
        }
        return new ArrayList<>(found);
    }

    private void backtrace(char[][] board, int i, int j, Set<String> found, TrieNode node) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }
        TrieNode child = node.children[board[i][j] - 'a'];
        if (child == null) {
            return;
        }
        if (child.word != null) {
            found.add(child.word);
        }
        char c = board[i][j];
        board[i][j] = '#';
        backtrace(board, i + 1, j, found, child);
        backtrace(board, i - 1, j, found, child);
        backtrace(board, i, j + 1, found, child);
        backtrace(board, i, j - 1, found, child);
        board[i][j] = c;
    }

    private void buildTrie(TrieNode node, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null) {
                node.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.word = word;
    }

    static class TrieNode{
        private TrieNode[] children = new TrieNode[26];
        String word = null;
    }
}
