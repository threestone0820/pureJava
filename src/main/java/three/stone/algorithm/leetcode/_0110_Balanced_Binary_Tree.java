package three.stone.algorithm.leetcode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 */
public class _0110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return depth(root) != -1;
    }

    // DFS
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 用 -1 这个特殊值表示不满足height-balanced
        int leftDepth = depth(node.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = depth(node.right);
        if (rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
