package three.stone.algorithm.leetcode;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * inorder and postorder consist of unique values.
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
        int rootVal = postorder[postEnd];
        TreeNode node = new TreeNode(rootVal);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        int leftLen = index - inStart;
        node.left = helper(inorder, inStart, index - 1, postorder, postStart, postStart + leftLen - 1);
        node.right = helper(inorder, index + 1, inEnd, postorder, postStart + leftLen, postEnd - 1);
        return node;
    }
}
