package three.stone.algorithm.leetcode;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = ""
 * Output: 0
 */
public class _0003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];

        int start = 0, end = 0, maxLen = Integer.MIN_VALUE;
        while (end < s.length()) {
            char c = s.charAt(end);
            map[c]++;
            // end++，一是实现自增遍历，二也是为了下面正确的求长度
            end++;
            // 求最大子串时，while循环中的条件是不满足要求的判断
            // 求最小子串时，while循环中的条件是满足要求的判断
            while (map[c] > 1) {
                map[s.charAt(start++)]--;
            }

            if (end - start > maxLen) {
                maxLen = end - start;
            }
        }

        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}
