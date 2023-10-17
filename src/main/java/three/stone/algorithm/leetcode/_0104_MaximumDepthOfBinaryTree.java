package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes
 * along the longest path from the root node down to the farthest leaf node.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class _0104_MaximumDepthOfBinaryTree {
    int max = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, 0);
        return max;
    }

    private void helper(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            max = Math.max(max, depth + 1);
        } else {
            if (root.left != null) {
                helper(root.left, depth + 1);
            }
            if (root.right != null) {
                helper(root.right, depth + 1);
            }
        }
    }

}
