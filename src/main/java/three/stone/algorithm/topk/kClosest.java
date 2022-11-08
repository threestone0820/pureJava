package three.stone.algorithm.topk;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
public class kClosest {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[0] * a[0] + a[1] * a[1]).reversed());
        for (int i = 0; i < k; i++) {
            maxHeap.offer(points[i]);
        }
        for (int i = k; i < points.length; i++) {
            int[] peek = maxHeap.peek();
            int[] cur = points[i];
            if (cur[0] * cur[0] + cur[1] * cur[1] < peek[0] * peek[0] + peek[1] * peek[1]) {
                maxHeap.poll();
                maxHeap.offer(cur);
            }
        }

        int[][] result = new int[k][];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}
