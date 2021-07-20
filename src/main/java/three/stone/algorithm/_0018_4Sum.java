package three.stone.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class _0018_4Sum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 0, nums.length - 1, target, 4);
    }

    private static List<List<Integer>> nSum(int[] nums, int low, int high, int target, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 2) {
            return twoSum(nums, low, high, target);
        }

        for (int i = low; i < high; i++) {
            if (i != low && nums[i] == nums[i - 1]) {
                continue;
            }

            int first = nums[i];
            List<List<Integer>> subSums = nSum(nums, i + 1, high, target - first, n - 1);
            for (List<Integer> subSum : subSums) {
                subSum.add(0, first);
                result.add(subSum);
            }
        }

        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int low, int high, int target) {
        List<List<Integer>> result = new ArrayList<>();
        while (low < high) {
            int first = nums[low];
            int second = nums[high];
            if (first + second == target) {
                result.add(new ArrayList<>(Arrays.asList(first, second)));

                // 必须 first + second == target 时才可以移动这两个指针
                while (low < high && nums[low] == first) {
                    low++;
                }
                while (low < high && nums[high] == second) {
                    high--;
                }
            } else if (first + second < target) {
                // 这时可以移动掉左边重复的
                low++;
            } else {
                // 这时可以移动掉右边重复的
                high--;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 0, 1, 2};
        Arrays.sort(nums);
        System.out.println(nSum(nums, 0, 4, 0, 3));
    }
}
