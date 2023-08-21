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

        // 把只有一行或一列的情况单独考虑
        if (sr == er) {
            for (int i = sc; i <= ec; i++) {
                result.add(matrix[sr][i]);
            }
        } else if (sc == ec) {
            for (int i = sr; i <= er; i++) {
                result.add(matrix[i][sc]);
            }
        }  else {
            for (int i = sc; i < ec; i++) {
                result.add(matrix[sr][i]);
            }
            for (int i = sr; i < er; i++) {
                result.add(matrix[i][ec]);
            }
            for (int i = ec; i > sc; i--) {
                result.add(matrix[er][i]);
            }
            for (int i = er; i > sr ; i--) {
                result.add(matrix[i][sc]);
            }

            visitHelper(matrix, result, sr + 1, sc + 1, er - 1, ec - 1);
        }
    }

}
