package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 *
 */
public class _0102_Binary_Tree_Level_Order_Traversal {
    // 先计算当前层的结点个数，这样就不用哨兵了
    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode removed = queue.poll();
                list.add(removed.val);
                if (removed.left != null) {
                    queue.offer(removed.left);
                }
                if (removed.right != null) {
                    queue.offer(removed.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
