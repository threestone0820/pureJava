package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
public class _0077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> temp, int n, int k, int cur) {
        int size = temp.size();
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if (k - size > n - cur + 1) {
            return;
        }

        for (int i = cur; i <= n; i++) {
            temp.add(i);
            backtrace(result, temp, n, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
