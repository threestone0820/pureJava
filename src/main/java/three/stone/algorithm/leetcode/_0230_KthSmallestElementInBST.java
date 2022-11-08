package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * 1 <= k <= n <= 104
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
 * and you need to find the kth smallest frequently, how would you optimize?
 */
public class _0230_KthSmallestElementInBST {

    private int count = 0, result = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node.left != null) {
            inOrder(node.left, k);
        }
        count--;
        if (count == 0) {
            result = node.val;
            return;
        }
        if (node.right != null) {
            inOrder(node.right, k);
        }
    }

    public int kthSmallestII(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return 0;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return result;
    }
}
