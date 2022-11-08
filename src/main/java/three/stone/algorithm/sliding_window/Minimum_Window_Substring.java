package three.stone.algorithm.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates)
 * is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 */
public class Minimum_Window_Substring {
    public static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char curChar = t.charAt(i);
            Integer curCount = map.getOrDefault(curChar, 0);
            map.put(curChar, curCount + 1);
        }

        int start = 0, end = 0, count = map.size();
        String result = null;
        while (end < s.length()) {
            char curChar = s.charAt(end++);
            if (map.containsKey(curChar)) {
                Integer curCount = map.get(curChar);
                if (curCount == 1) {
                    count--;
                }
                map.put(curChar, curCount - 1);
            }

            while (count == 0 && start < end) {
                if (result == null || end - start < result.length()) {
                    result = s.substring(start, end);
                }
                char skippedChar = s.charAt(start++);
                if (map.containsKey(skippedChar)) {
                    Integer skippedCharCount = map.get(skippedChar);
                    if (skippedCharCount == 0) {
                        count++;
                    }
                    map.put(skippedChar, skippedCharCount + 1);
                }
            }
        }

        return result == null ? "" : result;
    }
}
