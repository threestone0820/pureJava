package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * 1 <= k <= n <= 104
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
 * and you need to find the kth smallest frequently, how would you optimize?
 */

/**
 * 怎样让这个算法的复杂度达到O(log(N))呢：
 * 在二叉树节点中维护额外信息：每个节点需要记录当前结点所处的排序的位置
 * 比如说你让我查找排名为 k 的元素，当前节点知道自己排名第 m，那么我可以比较 m 和 k 的大小：
 *
 * 1、如果 m == k，显然就是找到了第 k 个元素，返回当前节点就行了。
 *
 * 2、如果 k < m，那说明排名第 k 的元素在左子树，所以可以去左子树搜索第 k 个元素。
 *
 * 3、如果 k > m，那说明排名第 k 的元素在右子树，所以可以去右子树搜索第 k - m个元素
 */
public class _0230_KthSmallestElementInBST {

    private int count = 0, result = 0;
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node.left != null) {
            inOrder(node.left, k);
        }
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        if (node.right != null) {
            inOrder(node.right, k);
        }
    }

    public int kthSmallestII(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return 0;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return result;
    }
}
