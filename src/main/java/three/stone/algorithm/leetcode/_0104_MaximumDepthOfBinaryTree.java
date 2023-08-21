package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes
 * along the longest path from the root node down to the farthest leaf node.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class _0104_MaximumDepthOfBinaryTree {
    int max = 0;

    // 是否可以通过遍历一遍二叉树得到答案？
    // 如果可以，用一个 traverse 函数配合外部变量来实现。
    public int maxDepthII(TreeNode root) {
        helper(root, 0);
        return max;
    }

    private void helper(TreeNode node, int curDepth) {
        if (node == null) {
            return;
        }
        max = Math.max(max, curDepth + 1);
        helper(node.left, curDepth + 1);
        helper(node.right, curDepth + 1);
    }

    // 是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？
    // 如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值。
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }

}
