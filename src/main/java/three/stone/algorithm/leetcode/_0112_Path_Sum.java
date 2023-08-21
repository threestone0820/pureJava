package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree
 * has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 */
public class _0112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        if (hasPathSum(root.left, targetSum - root.val)) {
            return true;
        }
        return hasPathSum(root.right, targetSum - root.val);
    }


}
