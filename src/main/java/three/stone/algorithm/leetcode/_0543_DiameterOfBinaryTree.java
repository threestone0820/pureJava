package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 */
public class _0543_DiameterOfBinaryTree {
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        postTraverse(root);
        return max;
    }

    // 后序遍历，并且需要子树的返回值
    private int postTraverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = postTraverse(node.left);
        int right = postTraverse(node.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }



}
