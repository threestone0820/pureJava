package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate
 * all combinations of well-formed parentheses.
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Input: n = 1
 * Output: ["()"]
 *
 */
public class _0022_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrace(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrace(List<String> result, StringBuilder builder, int left, int right, int n) {
        if (left == n && right == n) {
            result.add(builder.toString());
        } else {
            if (left < n) {
                builder.append('(');
                backtrace(result, builder, left + 1, right, n);
                builder.deleteCharAt(builder.length() - 1);
            }
            if (left > right) {
                builder.append(')');
                backtrace(result, builder, left, right + 1, n);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

}
