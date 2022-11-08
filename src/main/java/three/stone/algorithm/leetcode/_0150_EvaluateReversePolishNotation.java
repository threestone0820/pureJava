package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
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
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!operators.contains(token)) {
                stack.push(Integer.valueOf(token));
            } else {
                Integer value2 = stack.pop();
                Integer value1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(value1 + value2);
                        break;
                    case "-":
                        stack.push(value1 - value2);
                        break;
                    case "*":
                        stack.push(value1 * value2);
                        break;
                    case "/":
                        stack.push(value1 / value2);
                        break;
                }
            }
        }
        return stack.pop();
    }


}
