package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class _0054_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        visitHelper(matrix, result, 0, 0, matrix.length - 1, matrix[0].length - 1);
        return result;
    }

    private void visitHelper(int[][] matrix, List<Integer> result, int sr, int sc, int er, int ec) {
        if (sr > er || sc > ec) {
            return;
        }

        for (int i = sc; i <= ec; i++) {
            result.add(matrix[sr][i]);
        }

        for (int i = sr + 1; i <= er; i++) {
            result.add(matrix[i][ec]);
        }


        if (er > sr) {
            for (int i = ec - 1; i >= sc; i--) {
                result.add(matrix[er][i]);
            }
        }


        if (ec > sc) {
            for (int i = er - 1; i > sr; i--) {
                result.add(matrix[i][sc]);
            }
        }

        visitHelper(matrix, result, sr + 1, sc + 1, er - 1, ec - 1);
    }

}
