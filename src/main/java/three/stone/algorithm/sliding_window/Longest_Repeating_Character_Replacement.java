package three.stone.algorithm.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s and an integer k. You can choose any character of the string
 * and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get
 * after performing the above operations.
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class Longest_Repeating_Character_Replacement {

    // Accepted
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, maxRepeat = 0, result = 0;
        while (end < s.length()) {
            char curChar = s.charAt(end++);
            Integer count = map.get(curChar);
            count = count == null ? 1 : count + 1;
            map.put(curChar, count);
            maxRepeat = Math.max(maxRepeat, count);

            while ((end - start) > maxRepeat + k) {
                char skippedChar = s.charAt(start++);
                Integer count2 = map.get(skippedChar);
                if (count2 == 1) {
                    map.remove(skippedChar);
                } else {
                    map.put(skippedChar, count2 - 1);
                }
                // 此时通过遍历map来找到当前最大重复的字符个数，有什么更好的方案吗
                maxRepeat = findMaxRepeat(map);
            }
            result = Math.max(result, end - start);
        }

        return result;
    }

    private static int findMaxRepeat(Map<Character, Integer> map) {
        int result = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result = Math.max(result, entry.getValue());
        }
        return result;
    }
}
