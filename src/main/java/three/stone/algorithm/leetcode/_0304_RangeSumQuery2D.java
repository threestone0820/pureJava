package three.stone.algorithm.leetcode;

/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined
 * by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Explanation
 * NumMatrix numMatrix = new NumMatrix(
 * [
 *  [3, 0, 1, 4, 2],
 *  [5, 6, 3, 2, 1],
 *  [1, 2, 0, 1, 5],
 *  [4, 1, 0, 1, 7],
 *  [1, 0, 3, 0, 5]]);
 *
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8
 */
public class _0304_RangeSumQuery2D {
    class NumMatrix {

        private int[][] preSumMatrix;

        /**
         * 求子矩阵子数组的和 = 以右下角为顶点的矩阵 - 以左下角为顶点的矩阵 - 右上角为顶点的矩阵 + 左上角的重复部分
         *
         * 前缀和，存储时矩阵行列各+1可以避免考虑边界case
         * 前缀和主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和
         */
        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int column = matrix[0].length;
            preSumMatrix = new int[row + 1][column + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    preSumMatrix[i][j] = matrix[i - 1][j - 1]
                            + preSumMatrix[i - 1][j]
                            + preSumMatrix[i][j - 1]
                            - preSumMatrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSumMatrix[row2 + 1][col2 + 1] - preSumMatrix[row2 + 1][col1] - preSumMatrix[row1][col2 + 1] + preSumMatrix[row1][col1];
        }
    }
}
