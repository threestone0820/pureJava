package three.stone.algorithm.leetcode;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class _0045_Jump_Game_II {
    public int jump(int[] nums) {
        return helper(nums, nums.length - 1);
    }

    private int helper(int[] nums, int n) {
        if (n == 0) {
            return 0;
        }

        int index = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] >= n - i) {
                index = i;
            }
        }
        return 1 + helper(nums, index);
    }
}
