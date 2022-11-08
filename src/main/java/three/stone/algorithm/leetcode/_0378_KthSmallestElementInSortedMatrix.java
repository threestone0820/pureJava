package three.stone.algorithm.leetcode;

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
    private static class Tuple{
        int row;
        int column;
        int value;

        public Tuple(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> heap = new PriorityQueue<>(Comparator.comparingInt(t -> t.value));
        for (int i = 0; i < n; i++) {
            heap.offer(new Tuple(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple tuple = heap.poll();
            if (tuple.row == n - 1) {
                continue;
            }
            heap.offer(new Tuple(tuple.row + 1, tuple.column, matrix[tuple.row + 1][tuple.column]));
        }
        return heap.poll().value;
    }
}