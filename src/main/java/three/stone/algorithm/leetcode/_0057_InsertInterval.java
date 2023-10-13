package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 */
public class _0057_InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int len = intervals.length;
        if (len == 0 || newInterval[1] < intervals[0][0]) {
            result.add(newInterval);
            addAllRemainIntervals(result, intervals, 0);
        } else if (newInterval[0] > intervals[len - 1][1]) {
            addAllRemainIntervals(result, intervals, 0);
            result.add(newInterval);
        } else {
            int left = newInterval[0], right = newInterval[1];
            for (int i = 0; i < len; ) {
                int[] interval = intervals[i];
                if (interval[1] < left) {
                    result.add(interval);
                    i++;
                } else if (right < interval[0]) {
                    result.add(newInterval);
                    addAllRemainIntervals(result, intervals, i);
                    break;
                } else {
                    while (i < len && intervals[i][0] <= right) {
                        left = Math.min(left, intervals[i][0]);
                        right = Math.max(right, intervals[i][1]);
                        i++;
                    }
                    result.add(new int[]{left, right});
                    addAllRemainIntervals(result, intervals, i);
                    break;
                }
            }
        }
        return result.toArray(new int[0][0]);
    }

    private void addAllRemainIntervals(List<int[]> result, int[][] intervals, int index) {
        while (index < intervals.length) {
            result.add(intervals[index++]);
        }
    }
}
