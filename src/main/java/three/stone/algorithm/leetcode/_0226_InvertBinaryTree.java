package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 */
public class _0226_InvertBinaryTree {
    // 遍历的方式
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 分解问题（子树）的方式
    public TreeNode invertTreeII(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTreeII(root.left);
        TreeNode right = invertTreeII(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
