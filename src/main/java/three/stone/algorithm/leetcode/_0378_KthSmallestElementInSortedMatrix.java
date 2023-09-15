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
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            minHeap.offer(new int[]{i, 0, matrix[i][0]});
        }

        for (int i = 0; i < k - 1; i++) {
            int[] polled = minHeap.poll();
            int row = polled[0];
            int column = polled[1];
            if (column < length - 1) {
                minHeap.offer(new int[]{row, column + 1, matrix[row][column + 1]});
            }
        }
        return minHeap.poll()[2];
    }

    public int kthSmallestII(int[][] matrix, int k) {
        int row = matrix.length;
        int column = matrix[0].length;
        int low = matrix[0][0], high = matrix[row - 1][column - 1];
        int result;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (numberOfLessThan(matrix, mid) >= k) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    private int numberOfLessThan(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        int result = 0;
        for (int i = 0; i < row; i++) {
            int j = column - 1;
            while (j >= 0 && matrix[i][j] > target) {
                j--;
            }
            result += j + 1;
        }
        return result;
    }
}