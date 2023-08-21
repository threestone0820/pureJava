package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that
 * every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class _0076_Minimum_Window_Substring {
    public String minWindowII(String s, String t) {
        Map<Character, Integer> numMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            numMap.merge(c, 1, (oldVal, unused) -> oldVal + 1);
        }
        int finalStart = 0, finalEnd = Integer.MAX_VALUE, start = 0, end = 0, count = t.length();
        char[] chars = s.toCharArray();
        while (end < s.length()) {
            char ch = chars[end++];
            Integer num = numMap.get(ch);
            if (num != null) {
                numMap.put(ch, num - 1);
                if (num > 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (end - start < finalEnd - finalStart) {
                    finalEnd = end;
                    finalStart = start;
                }
                char ch2 = chars[start++];
                Integer num2 = numMap.get(ch2);
                if (num2 != null) {
                    numMap.put(ch2, num2 + 1);
                    if (num2 == 0) {
                        count++;
                    }
                }
            }
        }
        return finalEnd == Integer.MAX_VALUE ? "" : s.substring(finalStart, finalEnd);
    }
}
