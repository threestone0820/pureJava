package three.stone.algorithm.leetcode;

import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 * Input: matrix = [[1, 5, 9],
 *                  [10,11,13],
 *                  [12,13,15]
 *                 ], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 */
public class _0378_KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int value = matrix[i][j];
                if (heap.size() < k) {
                    heap.offer(value);
                } else {
                    if (value < heap.peek()) {
                        heap.poll();
                        heap.offer(value);
                    }
                }
            }
        }
        return heap.poll();
    }
}