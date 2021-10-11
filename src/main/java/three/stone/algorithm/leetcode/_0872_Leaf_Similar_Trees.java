package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * Consider all the leaves of a binary tree, from left to right order, the values
 * of those leaves form a leaf value sequence.
 */
public class _0872_Leaf_Similar_Trees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> sequence1 = new ArrayList<>();
        List<Integer> sequence2 = new ArrayList<>();

        leafValueSequence(root1, sequence1);
        leafValueSequence(root2, sequence2);
        return sequence1.equals(sequence2);
    }

    private void leafValueSequence(TreeNode node, List<Integer> sequence) {
        if (null == node) {
            return;
        }
        if (node.left == null && node.right == null) {
            sequence.add(node.val);
            return;
        }

        leafValueSequence(node.left, sequence);
        leafValueSequence(node.right, sequence);
    }
}
