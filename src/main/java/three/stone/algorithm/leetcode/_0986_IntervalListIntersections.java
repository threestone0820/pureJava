package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 */
public class _0986_IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int[] firstInterval = firstList[i];
            int[] secondInterval = secondList[j];
            if (firstInterval[1] < secondInterval[0]) {
                i++;
            } else if (secondInterval[1] < firstInterval[0]) {
                j++;
            } else {
                int left = Math.max(firstInterval[0], secondInterval[0]);
                int right = Math.min(firstInterval[1], secondInterval[1]);
                if (firstInterval[1] < secondInterval[1]) {
                    i++;
                } else {
                    j++;
                }
                result.add(new int[]{left, right});
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
