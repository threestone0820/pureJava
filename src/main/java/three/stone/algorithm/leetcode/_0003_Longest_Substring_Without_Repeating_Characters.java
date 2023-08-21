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
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, stop = 0, result = 0;
        while (stop < s.length()) {
            char ch = s.charAt(stop++);
            int count = map.compute(ch, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            if (count == 1) {
                result = Math.max(stop - start, result);
            }
            while (count == 2) {
                map.compute(s.charAt(start++), (key, oldValue) -> oldValue == 1 ? null : oldValue - 1);
                count = map.get(ch);
            }
        }
        return result;
    }
}
