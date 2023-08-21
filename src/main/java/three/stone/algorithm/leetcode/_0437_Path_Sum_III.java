package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths
 * where the sum of the values along the path equals targetSum.
 *   1
 *  2  -1
 *-1 2
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
public class _0437_Path_Sum_III {
    int result = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        return result;
    }

    private void dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return;
        }
        if (node.val == targetSum) {
            result++;
        }
        dfs(node.left, targetSum - node.val);
        dfs(node.right, targetSum - node.val);
    }
}
