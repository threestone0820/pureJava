package three.stone.algorithm.leetcode;

/**
 * Given the roots of two binary trees root and subRoot,
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 */
public class _0572_SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            if (subRoot == null) {
                return true;
            } else {
                return false;
            }
        }
        if (isSubtreeHelper(root, subRoot)) {
            return true;
        } else if (isSubtree(root.left, subRoot)) {
            return true;
        } else {
            return isSubtree(root.right, subRoot);
        }
    }

    private boolean isSubtreeHelper(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }

        return isSubtreeHelper(root.left, subRoot.left) && isSubtreeHelper(root.right, subRoot.right);
    }
}
