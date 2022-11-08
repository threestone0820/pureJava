package three.stone.algorithm.breadth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class ZigzagLevelOrderTraversal {
    // BFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean zigzagFlag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (zigzagFlag) {
                    level.add(0, node.val);
                } else {
                    level.add(node.val);
                }
            }
            zigzagFlag = !zigzagFlag;
            result.add(level);

        }
        return result;
    }

    // DFS
    public List<List<Integer>> zigzagLevelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        makeLevel(result, root, 0);
        return result;
    }

    private void makeLevel(List<List<Integer>> result, TreeNode node, int level) {
        if (null == node) {
            return;
        }

        if (result.size() == level) {
            result.add(new LinkedList<>());
        }

        makeLevel(result, node.left, level + 1);
        makeLevel(result, node.right, level + 1);
        if (level % 2 == 0) {
            result.get(level).add(node.val);
        } else {
            result.get(level).add(0, node.val);
        }
    }
}
