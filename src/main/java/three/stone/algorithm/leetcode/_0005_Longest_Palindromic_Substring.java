package three.stone.algorithm.leetcode;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 */
public class _0005_Longest_Palindromic_Substring {
    public String longestPalindrome(String s) {
        if (null == s || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        String result = "";
        boolean[][] dp = new boolean[length][length];

        for (int high = 0; high < length; high++) {
            for (int low = 0; low <= high; low++) {
                dp[low][high] = s.charAt(low) == s.charAt(high) &&
                        (high - low <= 2 || dp[low + 1][high - 1]);
                if (dp[low][high] && high - low + 1 > result.length()) {
                    result = s.substring(low, high + 1);
                }
            }
        }

        return result;
    }
}
