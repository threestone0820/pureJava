package three.stone.algorithm.leetcode;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * s consists only of lowercase English letters.
 */
public class _0516_LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        /**
         * dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
         * State transition:
         * dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
         * otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
         * Initialization: dp[i][i] = 1
         */
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
