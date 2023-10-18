package three.stone.algorithm.leetcode;

public class _0211_DesignAddAndSearchWordsDataStructure {

    class WordDictionary {

        private class Node{
            private boolean isWord = false;
            private Node[] children = new Node[26];

            public Node() {

            }
        }
        private Node root = new Node();

        public WordDictionary() {

        }

        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Node();
                }
                cur = cur.children[index];
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            return searchHelper(word, 0, root);
        }

        private boolean searchHelper(String word, int startIndex, Node startNode) {
            for (int i = startIndex; i < word.length(); i++) {
                if (word.charAt(i) == '.') {
                    for (Node child : startNode.children) {
                        if (child != null && searchHelper(word, i + 1, child)) {
                            return true;
                        }
                    }
                    return false;
                } else if (startNode.children[word.charAt(i) - 'a'] == null) {
                    return false;
                } else {
                    startNode = startNode.children[word.charAt(i) - 'a'];
                }
            }
            return startNode.isWord;
        }
    }
}
