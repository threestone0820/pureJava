package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest
 * valid (well-formed) parentheses substring.
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 */
public class _0032_Longest_Valid_Parentheses {

    /**
     * The workflow of the solution is as below.
     *
     * 1. Scan the string from beginning to end.
     *
     * 2. If current character is '(',
     * push its index to the stack. If current character is ')' and the
     * character at the index of the top of stack is '(', we just find a
     * matching pair so pop from the stack. Otherwise, we push the index of
     * ')' to the stack.
     *
     * 3. After the scan is done, the stack will only
     * contain the indices of characters which cannot be matched. Then
     * let's use the opposite side - substring between adjacent indices
     * should be valid parentheses.
     *
     * 4. If the stack is empty, the whole input
     * string is valid. Otherwise, we can scan the stack to get longest
     * valid substring as described in step 3.
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if (stack.isEmpty()) {
            return len;
        }
        int result = 0, a = len;
        while (!stack.isEmpty()) {
            Integer b = stack.pop();
            result = Math.max(result, a - b - 1);
            a = b;
        }
        // 注意：计算最左边的区间
        result = Math.max(result, a);
        return result;
    }
}
