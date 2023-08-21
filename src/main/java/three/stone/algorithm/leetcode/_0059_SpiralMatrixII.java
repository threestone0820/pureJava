package three.stone.algorithm.leetcode;

import java.util.List;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *  Input: n = 3
 * Output:
 * [[1,2,3],
 *  [8,9,4],
 *  [7,6,5]]
 */
public class _0059_SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        visitHelper(result, 1, 0, 0, n - 1, n - 1);
        return result;
    }

    private void visitHelper(int[][] matrix, int startValue, int sr, int sc, int er, int ec) {
        if (sr > er || sc > ec) {
            return;
        }

        // 把只有一行或一列的情况单独考虑
        if (sr == er) {
            for (int i = sc; i <= ec; i++) {
                matrix[sr][i] = startValue++;
            }
        } else if (sc == ec) {
            for (int i = sr; i <= er; i++) {
                matrix[i][sc] = startValue++;
            }
        }  else {
            for (int i = sc; i < ec; i++) {
                matrix[sr][i] = startValue++;
            }
            for (int i = sr; i < er; i++) {
                matrix[i][ec] = startValue++;
            }
            for (int i = ec; i > sc; i--) {
                matrix[er][i] = startValue++;
            }
            for (int i = er; i > sr ; i--) {
                matrix[i][sc] = startValue++;
            }

            visitHelper(matrix, startValue, sr + 1, sc + 1, er - 1, ec - 1);
        }
    }
}
