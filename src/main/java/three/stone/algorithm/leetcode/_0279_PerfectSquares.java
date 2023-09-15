package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer;
 * in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class _0279_PerfectSquares {
    public int numSquares(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            int perfect = i * i;
            if (perfect > n) {
                break;
            }
            set.add(perfect);
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j < i; j++) {
                    min = Math.min(min, dp[j] + dp[i - j]);
                }
                dp[i] = min;
            }
        }
        return dp[n];
    }
}
