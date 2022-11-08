package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

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
        int[] counter = new int[26];
        for (char c : p.toCharArray()) {
            counter[c - 'a']++;
        }

        int plen = p.length();
        int slen = s.length();
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        for (int i = 0; i < plen; i++) {
            counter[s.charAt(i) - 'a']--;
        }
        for (int i = 0; i <= slen - plen; i++) {
            if (allZero(counter)) {
                result.add(i);
            }
            counter[s.charAt(i) - 'a'] ++;
            if (i != slen - plen) {
                counter[s.charAt(i + plen) - 'a']--;
            }
        }
        return result;
    }

    private boolean allZero(int[] arr) {
        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
