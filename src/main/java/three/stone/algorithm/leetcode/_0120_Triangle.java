package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row, you may move to
 * either index i or index i + 1 on the next row.
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 */
public class _0120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                dp[0][0] = triangle.get(0).get(0);
                continue;
            }
            for (int j = 0; j <= i; j++) {
                int value = triangle.get(i).get(j);
                int minPath;
                if (j == 0) {
                    minPath = dp[i - 1][j];
                } else if (j == i) {
                    minPath = dp[i - 1][j - 1];
                } else {
                    minPath = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }
                dp[i][j] = minPath + value;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            result = Math.min(result, dp[size - 1][i]);
        }
        return result;
    }

}
