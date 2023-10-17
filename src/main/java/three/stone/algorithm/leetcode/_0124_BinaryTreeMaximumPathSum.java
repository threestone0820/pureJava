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

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = helper(node.left);
        int right = helper(node.right);
        int curVal = node.val;
        int curMax = curVal;
        if (left > 0) {
            curMax += left;
        }
        if (right > 0) {
            curMax += right;
        }
        max = Math.max(max, curMax);
        if (left < 0 && right < 0) {
            return curVal;
        }
        return curVal + Math.max(left, right);
    }
}
