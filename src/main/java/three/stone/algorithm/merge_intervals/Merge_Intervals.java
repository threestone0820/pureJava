package three.stone.algorithm.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class Merge_Intervals {
    // accepted
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; ) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= right) {
                // 千万注意，需要用最大值
                right = Math.max(right, intervals[j][1]);
                j++;
            }

            result.add(new int[]{left, right});
            i = j;
        }
        return result.toArray(new int[1][]);
    }
}
