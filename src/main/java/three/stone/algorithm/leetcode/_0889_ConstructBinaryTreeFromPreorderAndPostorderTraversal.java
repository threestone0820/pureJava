package three.stone.algorithm.leetcode;

/**
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal
 * of a binary tree of distinct values and postorder is the postorder traversal of the same tree,
 * reconstruct and return the binary tree.
 *
 * If there exist multiple answers, you can return any of them.
 */
public class _0889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        return helper(preorder, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode node = new TreeNode(rootVal);
        if (preStart == preEnd) {
            node.left = null;
            node.right = null;
            return node;
        }

        int leftRootVal = preorder[preStart + 1];
        int index = 0;
        for (int i = postStart; i < postEnd; i++) {
            if (postorder[i] == leftRootVal) {
                index = i;
                break;
            }
        }
        int leftLen = index - postStart + 1;

        node.left = helper(preorder, preStart + 1, preStart + leftLen, postorder, postStart, index);
        node.right = helper(preorder, preStart + leftLen + 1, preEnd, postorder, index + 1, postEnd - 1);
        return node;
    }
}
