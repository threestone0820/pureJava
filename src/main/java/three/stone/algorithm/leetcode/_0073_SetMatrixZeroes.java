package three.stone.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 */
public class _0073_SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }

        for (Integer r : rows) {
            for (int i = 0; i < column; i++) {
                matrix[r][i] = 0;
            }
        }

        for (Integer c : columns) {
            for (int i = 0; i < row; i++) {
                matrix[i][c] = 0;
            }
        }
    }
}
