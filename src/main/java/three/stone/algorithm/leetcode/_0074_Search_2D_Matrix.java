package three.stone.algorithm.leetcode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Input: matrix = [[1,3,5,7],
 *                  [10,11,16,20],
 *                  [23,30,34,60]], target = 3
 * Output: true
 */
public class _0074_Search_2D_Matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int num = matrix[mid / n][mid % n];
            if (num == target) {
                return true;
            } else if (num < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
