package three.stone.algorithm.leetcode;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and
 * return the subtree rooted with that node. If such a node does not exist, return null.
 */
public class _0700_SearchInBinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        return helper(root, val);
    }

    private TreeNode helper(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        if (node.val == target) {
            return node;
        } else if (node.val > target) {
            return helper(node.left, target);
        } else {
            return helper(node.right, target);
        }
    }
}
