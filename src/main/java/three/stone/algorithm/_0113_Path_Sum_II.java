package three.stone.algorithm;

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
        List<Integer> current = new ArrayList<>();
        preOrder(result, current, root, targetSum);
        return result;
    }

    private void preOrder(List<List<Integer>> result, List<Integer> current, TreeNode node, int target) {
        if (node == null) {
            return;
        }
        current.add(node.val);
        if (node.left == null && node.right == null && node.val == target) {
            result.add(new ArrayList<>(current));
        } else {
            preOrder(result, current, node.left, target - node.val);
            preOrder(result, current, node.right, target - node.val);
        }

        current.remove(current.size() - 1);
    }
}
