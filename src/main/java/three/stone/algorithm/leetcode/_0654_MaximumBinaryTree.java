package three.stone.algorithm.leetcode;

/**
 * You are given an integer array nums with no duplicates.
 * A maximum binary tree can be built recursively from nums using the following algorithm:
 *
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 */
public class _0654_MaximumBinaryTree {
    // 二叉树的构造问题一般都是使用「分解问题」的思路：
    // 构造整棵树 = 根节点 + 构造左子树 + 构造右子树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int[] max = new int[2];
        max[0] = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max[0]) {
                max[0] = nums[i];
                max[1] = i;
            }
        }
        TreeNode node = new TreeNode(max[0]);
        node.left = construct(nums, left, max[1] - 1);
        node.right = construct(nums, max[1] + 1, right);
        return node;
    }
}
