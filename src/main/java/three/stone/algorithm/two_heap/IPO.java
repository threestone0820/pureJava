package three.stone.algorithm.two_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO.
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i]
 * and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit
 * and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize your final capital,
 * and return the final maximized capital.
 */
public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> capQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> proQueue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]).reversed());
        for (int i = 0; i < profits.length; i++) {
            capQueue.offer(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < k; i++) {
            while (!capQueue.isEmpty() && capQueue.peek()[0] <= w) {
                proQueue.offer(capQueue.poll());
            }
            if (proQueue.isEmpty()) {
                break;
            }

            // 注意，最大堆中可能还有剩余，但是可以继续保持在此堆中
            // 因为 w 逐渐增大，剩余的 int[] 仍然满足 while (!capQueue.isEmpty() && capQueue.peek()[0] <= w)
            w += proQueue.poll()[1];
        }
        return w;
    }
}
