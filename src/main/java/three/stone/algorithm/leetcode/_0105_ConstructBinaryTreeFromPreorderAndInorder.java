package three.stone.algorithm.leetcode;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 */
public class _0105_ConstructBinaryTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart], leftLen = 0;
        while (inorder[inStart + leftLen] != rootVal) {
            leftLen++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftLen,
                inorder, inStart, inStart + leftLen - 1);
        root.right = buildTreeHelper(preorder, preStart + leftLen + 1, preEnd,
                inorder, inStart + leftLen + 1, inEnd);
        return root;
    }
}
