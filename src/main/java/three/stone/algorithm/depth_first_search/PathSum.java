package three.stone.algorithm.depth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a
 * root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode node, int target) {
        if (null == node) {
            return false;
        }

        if (node.left == null && node.right == null && node.val == target) {
            return true;
        } else if (dfs(node.left, target - node.val)) {
            return true;
        } else {
            return dfs(node.right, target - node.val);
        }
    }
}
