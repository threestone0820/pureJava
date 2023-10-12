package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote
 * can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * ransomNote and magazine consist of lowercase English letters.
 */
public class _0383_RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            Integer num = counter.getOrDefault(ch, 0);
            if (num == 0) {
                return false;
            }
            counter.put(ch, num - 1);
        }
        return true;
    }
}
