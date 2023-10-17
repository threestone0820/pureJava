package three.stone.algorithm.leetcode;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled
 * in a complete binary tree, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 */
public class _0222_CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = 1, rightDepth = 1;
        TreeNode node = root;
        while (node.left != null) {
            leftDepth++;
            node = node.left;
        }
        node = root;
        while (node.right != null) {
            rightDepth++;
            node = node.right;
        }
        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
}
