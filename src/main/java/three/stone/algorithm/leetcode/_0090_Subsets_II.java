package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class _0090_Subsets_II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> tempList, int[] nums, int index) {
        result.add(new ArrayList<>(tempList));
        for (int i = index; i < nums.length; ) {
            tempList.add(nums[i]);
            backtrace(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
            while (++i < nums.length && nums[i] == nums[i - 1]) {
            }
        }
    }
}
