package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that
 * every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 */
public class _0538_ConvertBSTToGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        inOrder(root, stack);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node.val += sum;
            sum = node.val;
        }
        return root;
    }

    private void inOrder(TreeNode node, Stack<TreeNode> stack) {
        if (node == null) {
            return;
        }

        inOrder(node.left, stack);
        stack.push(node);
        inOrder(node.right, stack);
    }
}
