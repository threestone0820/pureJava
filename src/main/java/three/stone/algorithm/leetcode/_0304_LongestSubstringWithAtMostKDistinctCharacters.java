package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string you need to print the size of the longest possible substring that
 * has exactly K unique characters. If there is no possible substring then print -1.
 *
 *
 * Example 1:
 *
 * Input:
 * S = "aabacbebebe", K = 3
 * Output: 7
 * Explanation: "cbebebe" is the longest
 * substring with K distinct characters.
 * Example 2:
 *
 * Input:
 * S = "aaaa", K = 2
 * Output: -1
 * Explanation: There's no substring with K
 * distinct characters.
 */
public class _0304_LongestSubstringWithAtMostKDistinctCharacters {
    public int longestkSubstr(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, stop = 0, len = s.length(), resultStart = -1, resultStop = -1;
        while (stop < len) {
            map.compute(s.charAt(stop++), (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            if (map.size() == k && stop - start > resultStop - resultStart) {
                resultStart = start;
                resultStop = stop;
            }
            while (map.size() > k) {
                map.compute(s.charAt(start++), (key, oldValue) -> oldValue > 1 ? oldValue - 1 : null);
            }
        }
        return resultStart == -1 ? -1 : resultStop - resultStart;
    }
}
