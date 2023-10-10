package three.stone.algorithm.leetcode;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Input: s = "babad"
 * Output: "bab"
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Input: s = "a"
 * Output: "a"
 */
public class _0005_Longest_Palindromic_Substring {
    //
    // dp

    /**
     * 注意处理数组时是如何减少重复运算的
     * 主要下面这个for循环，从前往后：
     * for (int end = 0; end < s.length(); end++) {
     *     for (int start = 0; start <= end; start++) {
     *
     *     }
     * }
     *
     * 从后往前也是类似：
     * for (int i = len - 1; i >= 0 ; i--) {
     *    dp[i][i] = true;
     *    for (int j = i + 1; j < len; j++) {
     *
     *    }
     * }
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, end = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = j == i + 1 || dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * extend Palindrome，其实也是双指针法
     * 巧妙的方式：回文子串一定是以某一个或两个字符为"中点"
     */
    private int start = 0, end = 0, maxLen = 1;
    public String longestPalindromeII(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            extendPalindrome(arr, i, i);
            if (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                extendPalindrome(arr, i, i + 1);
            }
        }
        return s.substring(start, end + 1);
    }

    private void extendPalindrome(char[] arr, int i, int j) {
        int len = arr.length;
        while (i >= 0 && j < len && arr[i] == arr[j]) {
            if (j - i + 1 > maxLen) {
                start = i;
                end = j;
                maxLen = j - i + 1;
            }
            i--;
            j++;
        }
    }
}
