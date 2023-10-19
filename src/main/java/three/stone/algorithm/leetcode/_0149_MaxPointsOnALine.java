package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _0149_MaxPointsOnALine {
    public static int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        Map<String, Set<String>> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int[] pointA = points[i];

            for (int j = i + 1; j < points.length; j++) {
                int[] pointB = points[j];
                int yDiff = pointB[1] - pointA[1];
                int xDiff = pointB[0] - pointA[0];
                int gcd = generateGCD(yDiff, xDiff);
                if (gcd != 0) {
                    yDiff /= gcd;
                    xDiff /= gcd;
                }
                String key = yDiff + "#" + xDiff;
                Set<String> pointList = map.getOrDefault(key, new HashSet<>());
                if (pointList.isEmpty()) {
                    pointList.add(pointA[0] + "_" + pointA[1]);
                }
                pointList.add(pointB[0] + "_" + pointB[1]);
                max = Math.max(max, pointList.size());
            }
        }
        return max;
    }

    private static int generateGCD(int a,int b){
        if (b==0) return a;
        else  return generateGCD(b,a%b);
    }

    public static void main(String[] args) {

        maxPoints(new int[][]{new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3}});



    }


}
