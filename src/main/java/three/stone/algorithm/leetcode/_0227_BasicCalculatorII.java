package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 *
 * Input: s = "3+2*2"
 * Output: 7
 *
 * Input: s = " 3/2 "
 * Output: 1
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 * Constraints:
 *
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 */
public class _0227_BasicCalculatorII {
    public int calculateMine(String s) {
        char[] chars = s.toCharArray();
        int result = 0, cur = 0, sign = 1;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)) {
                cur = (cur * 10 + (ch - '0'));
            } else if (ch == '+' || ch == '-') {
                result += cur * sign;
                cur = 0;
                sign = ch == '+' ? 1 : -1;
            } else if (ch == '*' || ch == '/') {
                int[] ints = nextDigit(s, chars, i);
                int nextVal = ints[0];
                i = ints[1];
                if (ch == '*') {
                    cur *= nextVal;
                } else {
                    cur /= nextVal;
                }

            }

        }
        if (cur != 0) {
            result += cur * sign;
        }
        return result;
    }

    private int[] nextDigit(String s, char[] chars, int index) {
        int[] result = new int[2];
        while (!Character.isDigit(chars[index])) {
            index++;
        }
        int endIndex = index;
        int value = 0;
        while (endIndex < chars.length && Character.isDigit(chars[endIndex])) {
            value = value * 10 + chars[endIndex] - '0';
            endIndex++;
        }
        result[0] = value;
        result[1] = endIndex - 1;
        return result;
    }

    public int calculate(String s) {
        s = s + "+";
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        char lastOp = '+';
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                cur = (cur * 10 + (ch - '0'));
            } else if (ch != ' ') {
                switch (lastOp) {
                    case '+':
                        stack.push(cur);
                        break;
                    case '-':
                        stack.push(-cur);
                        break;
                    case '*':
                        stack.push(cur * stack.pop());
                        break;
                    case '/':
                        stack.push(stack.pop() / cur);
                        break;
                }
                cur = 0;
                lastOp = ch;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

}
