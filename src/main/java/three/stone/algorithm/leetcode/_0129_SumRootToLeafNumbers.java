package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *
 * Each root-to-leaf path in the tree represents a number.
 *
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 */
public class _0129_SumRootToLeafNumbers {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int value) {
        int localValue = (value * 10) + node.val;
        if (node.left == null && node.right == null) {
            sum += localValue;
            return;
        }
        if (node.left != null) {
            dfs(node.left, localValue);
        }
        if (node.right != null) {
            dfs(node.right, localValue);
        }
    }


}
