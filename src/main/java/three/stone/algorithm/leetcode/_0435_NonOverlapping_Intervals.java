package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 */
public class _0435_NonOverlapping_Intervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        for (int i = 0; i < intervals.length; ) {
            int right = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] < right) {
                // 关键代码，移除右边界更大的那个，保留更小的
                right = Math.min(right, intervals[j][1]);
                count++;
                j++;
            }
            i = j;
        }

        return count;
    }
}
