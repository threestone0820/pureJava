package three.stone.algorithm;

/**
 * A peak element in a 2D grid is an element that is strictly greater than
 * all of its adjacent neighbors to the left, right, top, and bottom.
 *
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal,
 * find any peak element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 105
 * No two adjacent cells are equal.
 */
public class _1901_Find_A_Peak_Element_II {
    private int m, n;
    public int[] findPeakGrid(int[][] mat) {
        m = mat.length;
        n = mat[0].length;

        for (int i = 0; i < m; ++i) {
            int low = 0, high = n - 1;
            while (low <= high) {
                int mid = (low + high) >> 1;

            }
        }

        return null;
    }
}
