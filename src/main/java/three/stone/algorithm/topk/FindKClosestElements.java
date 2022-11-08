package three.stone.algorithm.topk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Pair> deap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            deap.offer(new Pair(i, Math.abs(arr[i] - x)));
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(arr[deap.poll().index]);
        }

        Collections.sort(result);

        return result;
    }

    private static class Pair implements Comparable<Pair> {
        int index;
        int diff;

        public Pair(int index, int diff) {
            this.index = index;
            this.diff = diff;
        }

        @Override
        public int compareTo(Pair o) {
            if (diff < o.diff) {
                return -1;
            } else if (diff > o.diff) {
                return 1;
            } else {
                return index - o.index;
            }
        }
    }
}
