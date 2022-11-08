package three.stone.algorithm.leetcode;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes
 * in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
public class _0124_BinaryTreeMaximumPathSum {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int curMax = root.val;
        int left = helper(root.left);
        int right = helper(root.right);
        if (left > 0) {
            curMax += left;
        }
        if (right > 0) {
            curMax += right;
        }
        max = Math.max(curMax, max);

        if (left < 0 && right < 0) {
            return root.val;
        } else {
            return root.val + Math.max(left, right);
        }
    }
}
