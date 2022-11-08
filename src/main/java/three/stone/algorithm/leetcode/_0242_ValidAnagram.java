package three.stone.algorithm.leetcode;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true*
 * Input: s = "rat", t = "car"
 * Output: false
 * s and t consist of lowercase English letters.
 */
public class _0242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
        }
        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
