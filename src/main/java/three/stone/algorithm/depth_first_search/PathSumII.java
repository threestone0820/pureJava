package three.stone.algorithm.depth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum. Each path should be returned as
 * a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(result, temp, root, targetSum);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> temp, TreeNode node, int remain) {
        if (node == null) {
            return;
        }

        temp.add(node.val);
        if (node.left == null && node.right == null && remain == node.val) {
            result.add(new ArrayList<>(temp));
        } else {
            dfs(result, temp, node.left, remain - node.val);
            dfs(result, temp, node.right, remain - node.val);
        }
        // 注意递归返回是，要把最后一个结点移除掉
        temp.remove(temp.size() - 1);
    }
}
