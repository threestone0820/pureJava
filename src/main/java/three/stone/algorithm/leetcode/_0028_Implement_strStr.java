package three.stone.algorithm.leetcode;

/**
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Example 3:
 *
 * Input: haystack = "", needle = ""
 * Output: 0
 */
public class _0028_Implement_strStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int length = needle.length();
        char startChar = needle.charAt(0);
        char[] chars = haystack.toCharArray();
        for (int i = 0; i <= chars.length - length; i++) {
            if (chars[i] == startChar &&
                    haystack.substring(i, i + length).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}
