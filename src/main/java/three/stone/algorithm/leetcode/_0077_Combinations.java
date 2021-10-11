package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 *
 * Example 1:
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
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class _0077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        backtrace(result, tempList, nums, 0, k);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> tempList,
                           List<Integer> nums, int start, int k) {
        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < nums.size(); ++i) {
            tempList.add(nums.get(i));
            // 是i + 1, 不是 start + 1
            backtrace(result, tempList, nums, i + 1, k);
            tempList.remove(tempList.size() - 1);
        }
    }
}
