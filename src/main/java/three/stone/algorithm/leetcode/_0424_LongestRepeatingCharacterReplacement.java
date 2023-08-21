package three.stone.algorithm.leetcode;

/**
 * You are given a string s and an integer k. You can choose any character of the string
 * and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * s consists of only uppercase English letters.
 */
public class _0424_LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        int start = 0, stop = 0, result = 0;
        while (stop < s.length()) {
            arr[s.charAt(stop++) - 'A']++;
            while (counter(arr) > k) {
                arr[s.charAt(start++) - 'A']--;
            }
            result = Math.max(stop - start, result);
        }
        return result;
    }

    private int counter(int[] arr) {
        int count = 0, max = 0;
        for (int num : arr) {
            count += num;
            max = Math.max(max, num);
        }
        return count - max;
    }
}
