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
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root, 0);
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }

    private void dfs(List<Integer> list, TreeNode node, int parent) {
        if (node == null) {
            return;
        }

        int value = parent * 10 + node.val;
        if (node.left == null && node.right == null) {
            list.add(value);
        } else {
            dfs(list, node.left, value);
            dfs(list, node.right, value);
        }
    }
}
