package three.stone.algorithm.two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 */
public class Four_Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; ) {
            for (int j = i + 1; j < nums.length - 2; ) {
                List<List<Integer>> twoSumResults = twoSum(nums, target - nums[i] - nums[j], j + 1, nums.length - 1);
                if (!twoSumResults.isEmpty()) {
                    for (List<Integer> twoSumResult : twoSumResults) {
                        twoSumResult.add(nums[i]);
                        twoSumResult.add(nums[j]);
                        result.add(twoSumResult);
                    }
                }
                // 跳过重复
                j++;
                while (j < nums.length - 2 && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
            // 跳过重复
            i++;
            while (i < nums.length - 3 && nums[i] == nums[i - 1]) {
                i++;
            }
        }

        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int target, int start, int end) {
        List<List<Integer>> result = new ArrayList<>();
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                result.add(new ArrayList<>(Arrays.asList(nums[start++], nums[end--])));
                while (start < end && nums[start] == nums[start - 1]) {
                    start++;
                }
                while (start < end && nums[end] == nums[end + 1]) {
                    end--;
                }
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }

        return result;
    }
}
