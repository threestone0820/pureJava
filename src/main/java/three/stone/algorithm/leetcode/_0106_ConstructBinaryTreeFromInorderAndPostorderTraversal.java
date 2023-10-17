package three.stone.algorithm.leetcode;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * inorder and postorder consist of unique values.
 *
 * inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 */
public class _0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return helper(inorder, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd], leftLen = 0;
        while (inorder[inStart + leftLen] != rootVal) {
            leftLen++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(inorder, inStart, inStart + leftLen - 1,
                postorder, postStart, postStart + leftLen - 1);
        root.right = helper(inorder, inStart + leftLen + 1, inEnd,
                postorder, postStart + leftLen, postEnd - 1);
        return root;
    }
}
