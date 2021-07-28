package three.stone.algorithm;

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
    public int longestValidParentheses(String s) {
        int length = s.length(), result = 0;
        if (length < 2) {
            return result;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
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
            return length;
        }

        int a = length, b = 0;
        while (!stack.isEmpty()) {
            b = stack.pop();
            result = Math.max(result, a - b - 1);
            a = b;
        }

        return result;
    }
}
