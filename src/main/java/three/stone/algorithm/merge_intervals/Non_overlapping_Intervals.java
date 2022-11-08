package three.stone.algorithm.merge_intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class Non_overlapping_Intervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int right = Integer.MIN_VALUE, result = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] < right) {
                right = Math.min(right, cur[1]);
                result++;
            } else {
                right = cur[1];
            }
        }

        return result;
    }
}
