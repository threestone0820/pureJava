package three.stone.algorithm;

import java.util.Stack;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 *  5
 * 4 6
 *   3  7
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 */
public class _0098_Validate_Binary_Search_Tree {
    TreeNode preNode = null;

    // 迭代的方式解决
    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }

        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val > root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    // 递归的方式解决
    public boolean isValidBST2(TreeNode root) {
        if (null == root) {
            return true;
        }

        return inorder(root);
    }

    private boolean inorder(TreeNode node) {
        if (null == node) {
            return true;
        }

        if (!inorder(node.left)) {
            return false;
        }
        if (preNode != null && node.val <= preNode.val) {
            return false;
        }
        preNode = node;
        return inorder(node.right);
    }

}
