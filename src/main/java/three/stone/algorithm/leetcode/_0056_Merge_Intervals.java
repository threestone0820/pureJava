package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 */
public class _0056_Merge_Intervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; ) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= right) {
                right = Math.max(right, intervals[j][1]);
                j++;

            }

            result.add(new int[]{left, right});
            i = j;
        }
        return result.toArray(new int[1][]);
    }
}
