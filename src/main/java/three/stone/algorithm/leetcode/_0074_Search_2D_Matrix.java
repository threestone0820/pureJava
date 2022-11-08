package three.stone.algorithm.leetcode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Input: matrix = [[1,3,5,7],
 *                  [10,11,16,20],
 *                  [23,30,34,60]], target = 3
 * Output: true
 */
public class _0074_Search_2D_Matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row - 1][column - 1]) {
            return false;
        }
        int low = 0, high = row * column - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cur = matrix[mid / column][mid % column];
            if (target == cur) {
                return true;
            } else if (target > cur) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
}
