package three.stone.algorithm.leetcode;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class _0003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[127];
        int start = 0, end = 0, max = Integer.MIN_VALUE;

        while (end < s.length()) {
            while (end < s.length() && chars[s.charAt(end)] == 0) {
                chars[s.charAt(end)] = 1;
                end++;
            }

            max = Math.max(end - start, max);
            if (end == s.length()) {
                return max;
            }
            while (s.charAt(start) != s.charAt(end)) {
                chars[s.charAt(start)] = 0;
                start++;
            }
            chars[s.charAt(start)] = 0;
            start++;
        }

        if (max == Integer.MIN_VALUE) {
            return 0;
        } else {
            return max;
        }
    }
}
