package three.stone.algorithm.depth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
public class BinaryTreeMaximumPathSum {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    int dfs(TreeNode node) {
        if (null == node) {
            return Integer.MIN_VALUE;
        }

        if (null == node.left && null == node.right) {
            result = Math.max(result, node.val);
            return node.val;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left > 0 && right > 0) {
            result = Math.max(left + right + node.val, result);
            return Math.max(left, right) + node.val;
        }

        int childrenMax = Math.max(left, right);
        if (childrenMax < 0) {
            result = Math.max(result, node.val);
            return node.val;
        } else {
            result = Math.max(result, node.val + childrenMax);
            return node.val + childrenMax;
        }
    }
}
