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
        Map<Character, Integer> map = new HashMap<>();
        int counter = t.length();
        for (char c : t.toCharArray()) {
            map.merge(c, 1, (old, unused) -> old + 1);
        }

        int left = 0, right = 0, min = Integer.MAX_VALUE;
        String result = "";
        char[] chars = s.toCharArray();
        while (right < chars.length) {
            char ch = chars[right++];
            Integer curCount = map.get(ch);
            if (null != curCount) {
                map.put(ch, curCount - 1);
                if (curCount.equals(1)) {
                    counter--;
                }
            }

            while (counter == 0) {
                if (right - left < min) {
                    min = right - left;
                    result = s.substring(left, right);
                }
                char ch2 = chars[left++];
                Integer curCount2 = map.get(ch2);
                if (curCount2 != null) {
                    map.put(ch2, curCount2 + 1);
                    if (curCount2 == 0) {
                        counter++;
                    }
                }
            }
        }

        return result;
    }

    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        int start = 0, end = 0, startResult = 0, endResult = 0, counter = t.length(), length = Integer.MAX_VALUE;
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (map[ch] > 0) {
                counter--;
            }
            map[ch]--;
            end++;
            // 求最小子串时，while循环中的条件是满足要求的判断
            while (counter == 0) {
                if (end - start < length) {
                    length = end - start;
                    startResult = start;
                    endResult = end;
                }

                if (map[s.charAt(start)] == 0) {
                    counter++;
                }
                map[s.charAt(start++)]++;
            }
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(startResult, endResult);
    }
}
