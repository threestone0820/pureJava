package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0039_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), candidates, target, 0, 0);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int sum, int index) {
        if (sum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target) {
            return;
        }

        // candidates = [2,3,6,7], target = 7
        for (int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            // 注意：这里index从i开始，而不是i + 1
            // 否则答案会变成: [7]
            backtrace(result, temp, candidates, target, sum + candidates[i], i);
            temp.remove(temp.size() - 1);
        }

        // 这也是错的，没有去重，答案为：[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]
//        for (int num : candidates) {
//            temp.add(num);
//            backtrace(result, temp, candidates, target, sum + num);
//            temp.remove(temp.size() - 1);
//        }
    }
}
