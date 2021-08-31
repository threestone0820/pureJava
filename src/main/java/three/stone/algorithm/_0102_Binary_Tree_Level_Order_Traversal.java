package three.stone.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 */
public class _0102_Binary_Tree_Level_Order_Traversal {
    // 增加了一个哨兵，来分隔每一层
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode sentinel = new TreeNode(0);
        if (null != root) {
            queue.add(root);
            queue.add(sentinel);
        }

        List<Integer> level = new ArrayList<>();
        while (queue.size() >= 1) {
            TreeNode node = queue.poll();
            if (node == sentinel) {
                queue.add(node);
                result.add(level);
                level = new ArrayList<>();
                if (queue.size() == 1) {
                    break;
                }
            } else {
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return result;
    }

    // 先计算当前层的结点个数，这样就不用哨兵了
    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (null != root) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelResult = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelResult.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(levelResult);
        }

        return result;
    }
}
