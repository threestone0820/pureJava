package three.stone.algorithm.leetcode;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class _0647_PalindromicSubstrings {
    public int countSubstrings(String s) {
        int result = 0;
        for (int i = 0; i < s.toCharArray().length; i++) {
            result += extendPalindromic(s, i, i);
            result += extendPalindromic(s, i, i + 1);
        }
        return result;
    }

    private int extendPalindromic(String s, int i, int j) {
        int count = 0;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }
}
