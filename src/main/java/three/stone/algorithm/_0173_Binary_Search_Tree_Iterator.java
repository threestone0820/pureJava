package three.stone.algorithm;

import java.util.Stack;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number,
 * the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number
 * in the in-order traversal when next() is called.
 *
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 */
public class _0173_Binary_Search_Tree_Iterator {
    class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            int val = node.val;
            TreeNode right = node.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
            return val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}

