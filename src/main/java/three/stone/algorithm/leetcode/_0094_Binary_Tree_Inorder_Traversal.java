package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */
public class _0094_Binary_Tree_Inorder_Traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(result, root);
        return result;
    }

    private void inorder(List<Integer> list, TreeNode root) {
        if (null == root) {
            return;
        }

        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);
    }
}
