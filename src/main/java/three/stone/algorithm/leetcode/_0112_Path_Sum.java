package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree
 * has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 */
public class _0112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }

        return preOrder(root, targetSum);
    }

    private boolean preOrder(TreeNode node, int target) {
        if (node.left == null && node.right == null) {
            if (node.val == target) {
                return true;
            } else {
                return false;
            }
        }

        boolean left = node.left != null && preOrder(node.left, target - node.val);
        // 左边为true就返回，右边就不用再递归了
        if (left) {
            return true;
        } else {
            return node.right != null && preOrder(node.right, target - node.val);
        }
    }
}
