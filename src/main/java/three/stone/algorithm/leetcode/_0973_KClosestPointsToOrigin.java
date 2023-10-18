package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 */
public class _0973_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                Comparator
                        .comparingInt((int[] arr) -> arr[2])
                        .reversed());
        for (int[] point : points) {
            int distinct = point[0] * point[0] + point[1] * point[1];
            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{point[0], point[1], distinct});
            } else {
                if (maxHeap.peek()[2] > distinct) {
                    maxHeap.poll();
                    maxHeap.offer(new int[]{point[0], point[1], distinct});
                }
            }
        }

        int[][] result = new int[k][];
        for (int i = 0; i < k; i++) {
            int[] polled = maxHeap.poll();
            result[i] = new int[]{polled[0], polled[1]};
        }
        return result;
    }
}
