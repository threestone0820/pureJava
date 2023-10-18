package three.stone.algorithm.leetcode;

public class _0208_ImplementTrie {
    private static class Node{
        private boolean isWord;
        private Node[] children;

        public Node() {
            children = new Node[26];
            isWord = false;
        }
    }
    private static class Trie {
        private Node root = new Node();

        public Trie() {

        }

        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                if (cur.children[word.charAt(i) - 'a'] == null) {
                    cur.children[word.charAt(i) - 'a'] = new Node();
                }
                cur = cur.children[word.charAt(i) - 'a'];
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                if (cur.children[word.charAt(i) - 'a'] == null) {
                    return false;
                }
                cur = cur.children[word.charAt(i) - 'a'];
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (cur.children[prefix.charAt(i) - 'a'] == null) {
                    return false;
                }
                cur = cur.children[prefix.charAt(i) - 'a'];
            }
            return true;
        }
    }
}
