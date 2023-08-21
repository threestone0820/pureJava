package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 */
public class _0257_BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, result, "");
        return result;
    }

    private void dfs(TreeNode node, List<String> result, String parent) {
        if (node == null) {
            return;
        }
        if (parent.isEmpty()) {
            parent = String.valueOf(node.val);
        } else {
            parent = parent + "->" + node.val;
        }
        if (node.left == null && node.right == null) {
            result.add(parent);
        } else {
            dfs(node.left, result, parent);
            dfs(node.right, result, parent);
        }
    }
}
