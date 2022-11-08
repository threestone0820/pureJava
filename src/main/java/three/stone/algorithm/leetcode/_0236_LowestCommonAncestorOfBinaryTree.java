package three.stone.algorithm.leetcode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class _0236_LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }
}
