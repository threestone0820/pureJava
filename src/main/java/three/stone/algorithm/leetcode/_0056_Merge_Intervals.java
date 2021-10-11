package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
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
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class _0056_Merge_Intervals {
    public int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = new int[2];
            interval[0] = intervals[i][0];
            int end = intervals[i][1];
            for (int j = i + 1; j < intervals.length; j++) {
                // 注意，要比较end的值，
                if (intervals[j][0] <= end) {
                    // i 和 j一起往后移动，因为j++, 所以j始终 = i + 1
                    i = j;
                    // eg: [1, 4], [2, 3]， 所以要用最大值作为边界
                    end = Math.max(end, intervals[j][1]);
                } else {
                    break;
                }
            }
            interval[1] = end;
            result.add(interval);
        }
        return result.toArray(new int[result.size()][]);
    }
}
