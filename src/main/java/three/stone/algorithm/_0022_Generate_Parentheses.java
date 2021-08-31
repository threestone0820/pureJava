package three.stone.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class _0022_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrace(result, n, n, "");
        return result;
    }

    private void backtrace(List<String> result, int leftNum, int rightNum, String temp) {
        if (leftNum == 0 && rightNum == 0) {
            result.add(temp);
            return;
        }

        // 注意，满足括号匹配的限制为：
        // at each step, you can either print "(" or ")", but print “)” only when there are more "(" than ")"
        if (leftNum > 0) {
            backtrace(result, leftNum - 1, rightNum, temp + "(");
        }
        if (leftNum < rightNum) {
            backtrace(result, leftNum, rightNum - 1, temp + ")");
        }
    }
}
