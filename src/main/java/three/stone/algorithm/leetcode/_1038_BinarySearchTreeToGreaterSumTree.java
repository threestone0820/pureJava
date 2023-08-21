package three.stone.algorithm.leetcode;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original
 * BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 */
public class _1038_BinarySearchTreeToGreaterSumTree {
    public TreeNode bstToGst(TreeNode root) {
        rightInLeft(root);
        return root;
    }

    int sum = 0;
    private void rightInLeft(TreeNode node) {
        if (node == null) {
            return;
        }
        rightInLeft(node.right);
        node.val += sum;
        sum = node.val;
        rightInLeft(node.left);
    }


}
