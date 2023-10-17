package three.stone.algorithm.leetcode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class _0236_LowestCommonAncestorOfBinaryTree {
    TreeNode result = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return result;
    }

    private boolean helper(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean leftFind = helper(node.left, p, q);
        boolean rightFind = helper(node.right, p, q);
        boolean curFind = node.val == p.val || node.val == q.val;
        if ((curFind && (leftFind || rightFind)) || (leftFind && rightFind)) {
            result = node;
        }
        return leftFind || rightFind || curFind;
    }
}
