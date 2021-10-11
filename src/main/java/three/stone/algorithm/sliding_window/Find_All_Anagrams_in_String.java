package three.stone.algorithm.sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class Find_All_Anagrams_in_String {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int length1 = s.length(), length2 = p.length();
        int[] count = new int[26];
        if (length1 < length2) {
            return result;
        }

        for (int i = 0; i < length2; i++) {
            count[p.charAt(i) - 'a']++;
            count[s.charAt(i) - 'a']--;
        }
        if (allZero(count)) {
            result.add(0);
        }

        for (int i = length2; i < length1; i++) {
            count[s.charAt(i) - 'a']--;
            count[s.charAt(i - length2) - 'a']++;
            if (allZero(count)) {
                // 注意index
                result.add(i - length2 + 1);
            }
        }

        return result;
    }

    private static boolean allZero(int[] count) {
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
