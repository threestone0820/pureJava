package three.stone.algorithm.leetcode;

import sun.tools.jstat.Jstat;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Input: s = "()"
 * Output: true
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 *
 * Input: s = "([)]"
 * Output: false
 * Example 5:
 *
 * Input: s = "{[]}"
 * Output: true
 *
 * s consists of parentheses only '()[]{}'.
 */
public class _0020_Valid_Parentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            if (ch1 == '(' || ch1 == '[' || ch1 == '{') {
                stack.push(ch1);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character ch2 = stack.pop();
                if ((ch1 == ')' && ch2 != '(') || (ch1 == ']' && ch2 != '[') || (ch1 == '}' && ch2 != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 逆向思维
     */
    public boolean isValidII(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
