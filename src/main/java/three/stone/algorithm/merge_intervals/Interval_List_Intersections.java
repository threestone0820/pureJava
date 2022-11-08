package three.stone.algorithm.merge_intervals;


import java.util.ArrayList;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi]
 * and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty
 * or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Input: firstList = [], secondList = [[4,8],[10,12]]
 * Output: []
 *
 * Input: firstList = [[1,7]], secondList = [[3,10]]
 * Output: [[3,7]]
 */
public class Interval_List_Intersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][0] > secondList[j][1]) {
                j++;
            } else if (firstList[i][1] < secondList[j][0]) {
                i++;
            } else {
                result.add(new int[] {
                        Math.max(firstList[i][0], secondList[j][0]),
                        Math.min(firstList[i][1], secondList[j][1])
                });
                if (firstList[i][1] > secondList[j][1]) {
                    j++;
                } else {
                    i++;
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
