package three.stone.algorithm.sliding_window;

import java.util.Arrays;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
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
public class Longest_Substring_Without_Repeating_Characters {
    // accepted
    public static int lengthOfLongestSubstring(String s) {
        int[] counter = new int[128];
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int start = 0, end = 0, result = 0;
        while (end < s.length()) {
            char c = s.charAt(end++);
            int count = counter[c];
            if (count == 0) {
                counter[c] = 1;
            } else {
                counter[c] = count + 1;
            }

            while (counter[c] > 1) {
                int count1 = counter[s.charAt(start)];
                counter[s.charAt(start)] = count1 - 1;
                start++;
            }

            result = Math.max(result, end - start);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

    }
}
