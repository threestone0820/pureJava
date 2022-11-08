package three.stone.algorithm.leetcode;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * Input: matrix = [[1,2,3],
 *                  [4,5,6],
 *                  [7,8,9]]
 * Output: [[7,4,1],
 *          [8,5,2],
 *          [9,6,3]]
 */
public class _0048_RotateImage {
    public void rotate(int[][] matrix) {
        // swap row
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // swap symmetry
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
