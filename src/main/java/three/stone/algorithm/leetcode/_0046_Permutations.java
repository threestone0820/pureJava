package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 * All the integers of nums are unique.
 */
public class _0046_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int num : nums) {
                if (!temp.contains(num)) {
                    temp.add(num);
                }
                backtrace(result, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
