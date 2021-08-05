package three.stone.algorithm;

/**
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates)
 * is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
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
 *
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class _0076_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        int[] chars = new int[127];
        for (char c : t.toCharArray()) {
            chars[c] += 1;
        }

        int start = 0, end = 0, count = t.length(), startResult = 0, endResult = 0, min = Integer.MAX_VALUE;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (chars[c] > 0) {
                count--;
            }
            chars[c]--;
            end++;

            while (count == 0) {
                if (end - start < min) {
                    min = end - start;
                    startResult = start;
                    endResult = end;
                }

                char startChar = s.charAt(start);
                chars[startChar]++;
                if (chars[startChar] > 0) {
                    count++;
                }
                start++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(startResult, endResult);
        }
    }
}
