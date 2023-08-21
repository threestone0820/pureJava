package three.stone.algorithm.leetcode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
public class _0235_LowestCommonAncestorOfBinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min, max;
        if (p.val < q.val) {
            min = p.val;
            max = q.val;
        } else {
            min = q.val;
            max = p.val;
        }

        while (root != null) {
            if (root.val >= min && root.val <= max) {
                return root;
            } else if (root.val < min) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root || (p.val > root.val && q.val < root.val) || (p.val < root.val && q.val > root.val)) {
            return root;
        }
        if (p.val > root.val) {
            return lowestCommonAncestorII(root.right, p, q);
        } else {
            return lowestCommonAncestorII(root.left, p, q);
        }
    }
}
