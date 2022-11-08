package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class _0078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> tempList, int[] nums, int index) {
        result.add(new ArrayList<>(tempList));

        for (int i = index; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrace(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
