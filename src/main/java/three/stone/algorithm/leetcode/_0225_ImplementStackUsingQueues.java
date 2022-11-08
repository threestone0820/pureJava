package three.stone.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 */
public class _0225_ImplementStackUsingQueues {
    private Queue<Integer> queue = new LinkedList<>();

    public _0225_ImplementStackUsingQueues() {

    }

    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        while (size-- > 0) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.size() == 0;
    }
}
