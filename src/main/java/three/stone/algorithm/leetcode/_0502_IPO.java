package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        int n = profits.length;
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{i, capital[i]});
        }
        while (k-- > 0) {
            while (minHeap.peek() != null && minHeap.peek()[1] <= w) {
                maxHeap.offer(profits[minHeap.poll()[0]]);
            }
            if (maxHeap.isEmpty()) {
                break;
            }
            w += maxHeap.poll();
        }
        return w;
    }

}
