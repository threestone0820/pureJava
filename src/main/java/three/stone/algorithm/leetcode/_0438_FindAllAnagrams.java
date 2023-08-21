package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * s and p consist of lowercase English letters.
 */
public class _0438_FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> needMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            needMap.merge(c, 1, (oldValue, unused) -> oldValue + 1);
        }
        int counter = needMap.size();
        int start = 0, end = 0;
        char[] chars = s.toCharArray();
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

            while (end - start == p.length()) {
                if (counter == 0) {
                    result.add(start);
                }
                char ch2 = chars[start++];
                Integer curCount2 = windowMap.get(ch2);
                if (curCount2 != null) {
                    windowMap.put(ch2, curCount2 - 1);
                    if (curCount2.equals(needMap.get(ch2))) {
                        counter++;
                    }
                }
            }
        }
        return result;
    }
}
