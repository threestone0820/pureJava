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
        recursion(n, result, 0, 0, "");
        return result;
    }

    private void recursion(int n, List<String> result, int left, int right, String temp) {
        if (left == n && right == n) {
            result.add(temp);
            return;
        }
        if (left < n) {
            recursion(n, result, left + 1, right, temp + "(");
        }
        if (left > right) {
            recursion(n, result, left, right + 1, temp + ")");
        }
    }
}
