package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Input: nums = []
 * Output: []
 *
 * Input: nums = [0]
 * Output: []
 */
public class _0015_3Sum {
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            List<List<Integer>> subResults = twoSum(nums, -nums[i], i + 1, nums.length - 1);
            if (!subResults.isEmpty()) {
                for (List<Integer> subResult : subResults) {
                    List<Integer> list = new ArrayList<>(subResult);
                    list.add(nums[i]);
                    result.add(list);
                }
            }
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return result;

    }

    private static List<List<Integer>> twoSum(int[] nums, int target, int i, int j) {
        List<List<Integer>> result = new ArrayList<>();
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                result.add(new ArrayList<>(Arrays.asList(nums[i++], nums[j--])));
                while (i < j && nums[i] == nums[i - 1]) {
                    i++;
                }
                while (i < j && nums[j] == nums[j + 1]) {
                    j--;
                }
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }
}
