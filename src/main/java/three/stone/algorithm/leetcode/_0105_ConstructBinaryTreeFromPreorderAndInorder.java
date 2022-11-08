package three.stone.algorithm.leetcode;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 */
public class _0105_ConstructBinaryTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd) {
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        int leftLength = rootIndex - inStart;
        if (leftLength == 0) {
            root.left = null;
        } else {
            root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftLength, inorder, inStart, rootIndex - 1);
        }
        int rightLength = inEnd - rootIndex;
        if (rightLength == 0) {
            root.right = null;
        } else {
            root.right = buildTreeHelper(preorder, preStart + leftLength + 1, preEnd, inorder, rootIndex + 1, inEnd);
        }
        return root;
    }
}
