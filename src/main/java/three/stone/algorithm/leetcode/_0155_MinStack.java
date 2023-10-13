package three.stone.algorithm.leetcode;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 */
public class _0155_MinStack {
    private static class Node {
        int val;
        int minVal;

        public Node(int val, int minVal) {
            this.val = val;
            this.minVal = minVal;
        }
    }
    private Stack<Node> stack = new Stack<>();

    public _0155_MinStack() {

    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Node(val, val));
        } else {
            int curMin = stack.peek().minVal;
            stack.push(new Node(val, Math.min(curMin, val)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().minVal;
    }
}
