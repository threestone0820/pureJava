package three.stone.algorithm.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order
 * by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 */
public class Insert_Interval {
    // accepted
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        if (intervals.length == 0) {
            result.add(newInterval);
            return result.toArray(new int[1][]);
        }
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            result.addAll(Arrays.asList(intervals));
            result.add(newInterval);
            return result.toArray(new int[result.size()][]);
        }
        if (newInterval[1] < intervals[0][0]) {
            result.add(newInterval);
            result.addAll(Arrays.asList(intervals));
            return result.toArray(new int[result.size()][]);
        }

        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        if (newInterval[1] < intervals[i][0]) {
            result.add(newInterval);
        } else if (newInterval[1] >= intervals[i][0] && newInterval[1] < intervals[i][1]) {
            result.add(new int[]{Math.min(intervals[i][0], newInterval[0]), intervals[i][1]});
            i++;
        } else {
            int left = Math.min(intervals[i][0], newInterval[0]);
            int right = Math.max(intervals[i][1], newInterval[1]);
            while (i < intervals.length) {
                if (right >= intervals[i][0]) {
                    right = Math.max(right, intervals[i][1]);
                    i++;
                } else {
                    break;
                }
            }
            result.add(new int[]{left, right});
        }

        while (i < intervals.length) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[result.size()][]);
    }
}
