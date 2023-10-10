package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = ""
 * Output: 0
 */
public class _0003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, stop = 0, maxLen = 0;
        Map<Character, Integer> counter = new HashMap<>();
        while (stop < s.length()) {
            char c = s.charAt(stop++);
            Integer count = counter.merge(c, 1, (oldValue, unused) -> oldValue + 1);
            while (count != 1) {
                counter.merge(s.charAt(start++), 0, (oldValue, unused) -> oldValue - 1);
                count = counter.get(c);
            }
            maxLen = Math.max(maxLen, stop - start);
        }
        return maxLen;
    }
}
