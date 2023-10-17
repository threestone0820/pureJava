package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 Given the root of a binary tree, flatten the tree into a "linked list":
 The "linked list" should use the same TreeNode class where the right child pointer points
 to the next node in the list and the left child pointer is always null.
 The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class _0114_Flatten_Binary_Tree_to_Linked_List {

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(list, root);
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = i == list.size() - 1 ? null : list.get(i + 1);
        }
    }

    private void preOrder(List<TreeNode> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node);
        preOrder(list, node.left);
        preOrder(list, node.right);
    }
}
