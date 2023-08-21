package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 */
public class _0567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> needMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            needMap.merge(ch, 1, (oldValue, unused) -> oldValue + 1);
        }
        int counter = needMap.size();
        char[] chars = s2.toCharArray();
        int start = 0, end = 0;
        while (end < chars.length) {
            char ch = chars[end++];
            Integer needCount = needMap.get(ch);
            if (needCount != null) {
                Integer curCount = windowMap.getOrDefault(ch, 0);
                windowMap.put(ch, curCount + 1);
                if (curCount + 1 == needCount) {
                    counter--;
                }
            }

            // while 改成了if，因为start++一定会破会条件
            if (end - start == s1.length()) {
                if (counter == 0) {
                    return true;
                }

                char ch2 = chars[start++];
                Integer windowCount = windowMap.get(ch2);
                if (windowCount != null) {
                    windowMap.put(ch2, windowCount - 1);
                    if (windowCount.equals(needMap.get(ch2))) {
                        counter++;
                    }
                }
            }
        }
        return false;
    }
}
