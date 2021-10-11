package three.stone.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class _0016_3Sum_Closest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int currentSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i <= nums.length - 3; i++) {
            int first = nums[i];
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int second = nums[low];
                int third = nums[high];
                if (Math.abs(target - currentSum) > Math.abs(target - (first + second + third))) {
                    currentSum = (first + second + third);
                }

                if (second + third > (target - first)) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return currentSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 1, -3};
        System.out.println(threeSumClosest(nums, 1));
    }
}
