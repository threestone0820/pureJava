package three.stone.algorithm.leetcode;

import java.util.Stack;

public class _0530_MinimumAbsoluteDifferenceInBST {
    int result = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return result;
    }
    
    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (prev != null) {
            result = Math.min(result, Math.abs(node.val - prev.val));
        }
        prev = node;
        inorder(node.right);
    }
}
