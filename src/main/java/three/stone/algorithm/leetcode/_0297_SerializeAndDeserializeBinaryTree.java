package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string
 * can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as [link](how LeetCode serializes a binary tree.)
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 */
public class _0297_SerializeAndDeserializeBinaryTree {
    private String SEPERATOR = ",";
    private String NULL = "X";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preOrderSerialize(root, builder);
        builder.deleteCharAt(0);
        return builder.toString();
    }

    private void preOrderSerialize(TreeNode node, StringBuilder builder) {
        if (node == null) {
            builder.append(SEPERATOR);
            builder.append(NULL);
        } else {
            builder.append(SEPERATOR);
            builder.append(node.val);
            preOrderSerialize(node.left, builder);
            preOrderSerialize(node.right, builder);
        }
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> values = new LinkedList<>(Arrays.asList(data.split(SEPERATOR)));
        return preOrderDeserialize(values);
    }

    private TreeNode preOrderDeserialize(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals(NULL)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = preOrderDeserialize(queue);
            node.right = preOrderDeserialize(queue);
            return node;
        }
    }
}
