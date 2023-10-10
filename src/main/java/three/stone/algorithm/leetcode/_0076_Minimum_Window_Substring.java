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
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        int start = 0, stop = 0, minLen = Integer.MAX_VALUE, num = t.length(), findNum = 0, finalStart = 0, finalStop = 0;
        while (stop < s.length()) {
            char c = s.charAt(stop++);
            Integer needCount = counter.get(c);
            if (needCount != null) {
                if (needCount > 0) {
                    findNum++;
                }
                counter.put(c, needCount - 1);
                while (findNum == num) {
                    if (stop - start < minLen) {
                        minLen = stop - start;
                        finalStart = start;
                        finalStop = stop;
                    }
                    char removed = s.charAt(start++);
                    Integer removedCount = counter.get(removed);
                    if (removedCount != null) {
                        if (removedCount == 0) {
                            findNum--;
                        }
                        counter.put(removed, removedCount + 1);
                    }
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(finalStart, finalStop);
    }
}
