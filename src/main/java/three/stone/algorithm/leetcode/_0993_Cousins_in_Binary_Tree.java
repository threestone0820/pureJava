package three.stone.algorithm.leetcode;

/**
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
 * return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 *
 * The number of nodes in the tree is in the range [2, 100].
 * 1 <= Node.val <= 100
 * Each node has a unique value.
 * x != y
 * x and y are exist in the tree.
 */
public class _0993_Cousins_in_Binary_Tree {
    public static boolean isCousins(TreeNode root, int x, int y) {
        int[] depths = {-1, -1, -1, -1};
        findNode(root, null, x, y, depths, 0);
        return depths[0] == depths[2] && depths[1] != depths[3];
    }

    private static void findNode(TreeNode node, TreeNode parent, int x, int y, int[] depths, int currentDepth) {
        if (null == node) {
            return;
        }
        if (node.val == x) {
            depths[0] = currentDepth;
            if (null != parent) {
                depths[1] = parent.val;
            }
        }
        if (node.val == y) {
            depths[2] = currentDepth;
            if (null != parent) {
                depths[3] = parent.val;
            }
        }

        if (depths[0] != -1 && depths[2] != -1) {
            return;
        }

        findNode(node.left, node, x, y, depths, currentDepth + 1);
        findNode(node.right, node, x, y, depths, currentDepth + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, null, null));
        System.out.println(isCousins(root, 2, 3));
    }
}
