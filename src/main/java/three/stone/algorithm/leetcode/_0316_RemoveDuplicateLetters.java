package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class _0316_RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // 如果需要获得某个子串，也许会用到滑动窗口、动态规划、二分查找
        // 但是这题是要得到不连续的子串，并且字符顺序不能更改，似乎这种题目要用栈来解决

        char[] chars = s.toCharArray();
        // 这两个数组（或者说map）可以帮助我们记录一些东西
        boolean[] visited = new boolean[26];
        int[] count = new int[26];

        for (char ch : chars) {
            count[ch - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            int index = ch - 'a';
            count[index]--;
            if (visited[index]) {
                continue;
            }
            // 单调栈变形？，只是pop时需要一些额外条件
            while (!stack.isEmpty() && stack.peek() > ch && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(ch);
            visited[index] = true;
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            // 头插法，因为需要栈中元素的逆序
            builder.insert(0, stack.pop());
        }
        return builder.toString();
    }
}
