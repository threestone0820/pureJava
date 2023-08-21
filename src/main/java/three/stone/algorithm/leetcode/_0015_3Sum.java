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
        int i = 0;
        while (i <= nums.length - 3) {
            int first = nums[i];
            List<List<Integer>> list = twoSum(nums, -first, i + 1, nums.length - 1);
            if (!list.isEmpty()) {
                for (List<Integer> l : list) {
                    List<Integer> temp = new ArrayList<>(l);
                    temp.add(first);
                    result.add(temp);
                }
            }

            while (i <= nums.length - 3 && nums[i] == first) {
                i++;
            }
        }
        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int target, int i, int j) {
        List<List<Integer>> result = new ArrayList<>();
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                int second = nums[i];
                int third = nums[j];
                result.add(Arrays.asList(second, third));

                // 去重
                while (i < j && second == nums[i]) {
                    i++;
                }

                // 去重
                while (i < j && third == nums[j]) {
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
