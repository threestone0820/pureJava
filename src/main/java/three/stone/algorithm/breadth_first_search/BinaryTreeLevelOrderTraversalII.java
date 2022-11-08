package three.stone.algorithm.breadth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 */
public class BinaryTreeLevelOrderTraversalII {

    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                level.add(node.val);
            }
            result.add(0, level);
        }

        return result;
    }

    // DFS
    public List<List<Integer>> levelOrderBottomII(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        levelMaker(result, root, 0);
        return result;
    }

    private void levelMaker(List<List<Integer>> result, TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (level == result.size()) {
            result.add(0, new ArrayList<>());
        }

        levelMaker(result, node.left, level + 1);
        levelMaker(result, node.right, level + 1);
        result.get(result.size() - 1 - level).add(node.val);
    }
}
