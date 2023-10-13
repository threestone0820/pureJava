package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class _0452_MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(point -> point[0]));
        int left = points[0][0], right = points[0][1], index = 1, result = 1;
        while (index < points.length) {
            int[] point = points[index];
            if (point[0] > right) {
                result++;
                left = point[0];
                right = point[1];
            } else {
                left = Math.min(left, point[0]);
                right = Math.min(right, point[1]);
            }
            index++;
        }
        return result;
    }
}
