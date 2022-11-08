package three.stone.algorithm.sub_set;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements,
 * return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets.
 * Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        backtrace(result, tempList, nums, 0);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrace(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
