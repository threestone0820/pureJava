package three.stone.algorithm.leetcode;

/**
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class _0099_Recover_Binary_Search_Tree {
    // 可能根节点的值就是Integer.MIN_VALUE，所以可以用prevNode = null来解决
    private TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);
    private TreeNode firstNode = null;
    private TreeNode secondNode = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (null == firstNode && prevNode.val >= node.val) {
            // 第一个使用prev
            firstNode = prevNode;
        }
        if (null != firstNode && prevNode.val >= node.val) {
            // 第二个使用当前node
            secondNode = node;
        }
        prevNode = node;
        inorder(node.right);
    }
}
