package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most
 * m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is
 * {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 */
public class _0474_OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = numberOfZeroOne(str);
            int zero = count[0];
            int one = count[1];
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zero][j - one]);
                }
            }
        }
        return dp[m][n];
    }

    private int[] numberOfZeroOne(String s) {
        int zero = 0, one = 0;
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '0') {
                zero++;
            } else {
                one++;
            }
        }
        return new int[]{zero, one};
    }
}
