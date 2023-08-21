package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where each path's sum equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class _0113_Path_Sum_II {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), root, targetSum);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, TreeNode node, int target) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null && node.val == target) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(result, path, node.left, target - node.val);
            dfs(result, path, node.right, target - node.val);
        }
        path.remove(path.size() - 1);
    }


}
