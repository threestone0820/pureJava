package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given n projects where the ith project has a pure profit profits[i]
 * and a minimum capital of capital[i] is needed to start it.
 *
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit
 * and the profit will be added to your total capital.
 *
 * Pick a list of at most k distinct projects from given projects to maximize
 * your final capital, and return the final maximized capital.
 */
public class _0502_IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> capitalHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        PriorityQueue<Integer> profitHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int result = w;
        for (int i = 0; i < capital.length; i++) {
            capitalHeap.offer(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < k; i++) {
            while (!capitalHeap.isEmpty() && w >= capitalHeap.peek()[0]) {
                profitHeap.offer(capitalHeap.poll()[1]);
            }
            if (!profitHeap.isEmpty()) {
                Integer profit = profitHeap.poll();
                result += profit;
                w += profit;
            }
        }
        return result;
    }

}
