package three.stone.algorithm;

/**
 *
 Given the root of a binary tree, flatten the tree into a "linked list":
 The "linked list" should use the same TreeNode class where the right child pointer points
 to the next node in the list and the left child pointer is always null.
 The "linked list" should be in the same order as a pre-order traversal of the binary tree.

 1
 / \
 2   5
 / \   \
 3   4   6
 -----------
 pre = 5
 cur = 4

 1
 /
 2
 / \
 3   4
 \
 5
 \
 6
 -----------
 pre = 4
 cur = 3

 1
 /
 2
 /
 3
 \
 4
 \
 5
 \
 6
 -----------
 cur = 2
 pre = 3

 1
 /
 2
 \
 3
 \
 4
 \
 5
 \
 6
 -----------
 cur = 1
 pre = 2

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6

 */
public class _0114_Flatten_Binary_Tree_to_Linked_List {
    //全局变量
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
