package three.stone.algorithm.breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect the nodes that are at same level.
 * You'll be given an addition nextRight pointer for the same.
 *
 * Initially, all the nextRight pointers point to garbage values.
 * Your function should set these pointers to point next right for each node.
 *        10                       10 ------> NULL
 *       / \                       /      \
 *      3   5       =>            3 ------> 5 --------> NULL
 *     / \     \               /  \           \
 *    4   1   2               4 --> 1 -----> 2 -------> NULL
 */
public class ConnectNodesAtSameLevel {
    public void connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 得出当前层有多少结点
            int size = queue.size();
            Node tail = null;
            for (int i = 0; i < size; ++i) {
                Node next = queue.poll();
                if (next.left != null) {
                    queue.offer(next.left);
                }
                if (next.right != null) {
                    queue.offer(next.right);
                }
                if (i != 0) {
                    tail.nextRight = next;
                }
                tail = next;
            }
            tail.nextRight = null;
        }
    }

    private static class Node{
        int data;
        Node left;
        Node right;
        Node nextRight;
        Node(int data){
            this.data = data;
            left=null;
            right=null;
            nextRight = null;
        }
    }
}
