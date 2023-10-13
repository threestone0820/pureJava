package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class _0150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Set<String> ops = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (ops.contains(token)) {
                Integer op2 = stack.pop();
                Integer op1 = stack.pop();
                if ("+".equals(token)) {
                    stack.push(op1 + op2);
                } else if ("-".equals(token)) {
                    stack.push(op1 - op2);
                } else if ("*".equals(token)) {
                    stack.push(op1 * op2);
                } else {
                    stack.push(op1 / op2);
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }


}
