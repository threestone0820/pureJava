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
        char[] chars = s.toCharArray();
        int len = chars.length;
        int low = 0, high = 0, result = Integer.MIN_VALUE;
        while (high < len) {
            arr[chars[high++] - 'A']++;
            while (counter(arr) > k) {
                arr[chars[low++] - 'A']--;
            }
            result = Math.max(result, high - low);
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
