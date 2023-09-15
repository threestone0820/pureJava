package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane,
 * where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance
 * between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 */
public class _1584_MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        int length = points.length;
        boolean[] connected = new boolean[length];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        connected[0] = true;
        int[] point0 = points[0];
        int connectedPoint = 1;
        for (int i = 1; i < length; i++) {
            minHeap.offer(new int[]{i, manhattanDistance(points[i], point0)});
        }
        int result = 0;
        while (connectedPoint < length) {
            int[] polled = minHeap.poll();
            int polledIndex = polled[0];
            if (connected[polledIndex]) {
                continue;
            }

            result += polled[1];
            connectedPoint++;
            int[] polledPoint = points[polledIndex];
            connected[polledIndex] = true;

            for (int i = 0; i < length; i++) {
                if (connected[i]) {
                    continue;
                }
                minHeap.offer(new int[]{polledIndex, manhattanDistance(polledPoint, points[i])});
            }
        }

        return result;
    }

    private int manhattanDistance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}
