package three.stone.algorithm.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring in it
 * with no more than K distinct characters.
 * Example 1:
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 * Example 2:
 * Input: String="araaci", K=1
 * Output: 2
 * Explanation: The longest substring with no more than '1' distinct characters is "aa".
 * Example 3:
 * Input: String="cbbebi", K=3
 * Output: 5
 * Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 */
public class Longest_Substring_With_K_Distinct_Characters {
    public int longestkSubstr(String s, int k) {
        if (null == s || s.isEmpty()) {
            return -1;
        }

        int start = 0, end = 0, result = -1;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char curChar = s.charAt(end++);
            map.merge(curChar, 1, (oldValue, unused) -> oldValue + 1);
            if (map.size() == k) {
                result = Math.max(result, end - start);
            }

            while (map.size() > k) {
                char skippedChar = s.charAt(start++);
                Integer count = map.get(skippedChar);
                if (count == 1) {
                    map.remove(skippedChar);
                } else {
                    map.put(skippedChar, count - 1);
                }
            }
        }

        return result;
    }
}
