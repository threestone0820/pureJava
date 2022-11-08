package three.stone.algorithm.sub_set;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate
 * all combinations of well-formed parentheses.
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 */
public class BalancedParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrace(result, "", 0, 0, n);
        return result;
    }

    private void backtrace(List<String> result, String temp, int left, int right, int n) {
        if (left == n && right == n) {
            result.add(temp);
        } else {
            if (left < n) {
                backtrace(result, temp + "(", left + 1, right, n);
            }
            if (right < left) {
                backtrace(result, temp + ")", left, right + 1, n);
            }
        }
    }
}
