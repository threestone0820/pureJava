package three.stone.algorithm.depth_first_search;

import three.stone.algorithm.leetcode.TreeNode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {
    // 时间复杂度糟糕
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        int diameter1 =  left + right;
        int diameter2 = diameterOfBinaryTree(root.left);
        int diameter3 = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(diameter1, diameter2), diameter3);
    }

    private int dfs(TreeNode node) {
        if (null == node) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);
        return Math.max(left, right) + 1;
    }


    // 时间复杂度好一些
    private int max = 0;
    public int diameterOfBinaryTreeII(TreeNode root) {
        dfsII(root);
        return max;
    }

    private int dfsII(TreeNode node) {
        if (null == node) {
            return 0;
        }

        int left = dfsII(node.left);
        int right = dfsII(node.right);
        // 下面两行是核心代码，
        // 直径是path的值，是path中的结点数-1，所以下面代码是left + right，没有+1
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }


}
