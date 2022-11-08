package three.stone.algorithm.two_pointer;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers
 * in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class Three_Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int curSum = nums[j] + nums[k] + first;
                if (Math.abs(target - curSum) < diff) {
                    result = curSum;
                    diff = Math.abs(target - curSum);
                }
                if (curSum == target) {
                    return curSum;
                } else if (curSum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return result;
    }
}
