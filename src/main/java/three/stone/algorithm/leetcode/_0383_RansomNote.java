package three.stone.algorithm.leetcode;

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
        int[] counter = new int[26];
        for (char c : ransomNote.toCharArray()) {
            counter[c - 'a']++;
        }
        for (char c : magazine.toCharArray()) {
            counter[c - 'a']--;
        }
        for (int i : counter) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }
}
