package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 */
public class _0098_Validate_Binary_Search_Tree {
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
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    // 递归的方式解决
    TreeNode preNode = null;
    public boolean isValidBST2(TreeNode root) {
        return inOrderBST(root);
    }

    private boolean inOrderBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!inOrderBST(node.left)) {
            return false;
        }
        if (preNode != null && preNode.val >= node.val) {
            return false;
        }
        preNode = node;
        return inOrderBST(node.right);
    }

}
