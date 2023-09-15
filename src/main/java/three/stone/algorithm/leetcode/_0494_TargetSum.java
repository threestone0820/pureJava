package three.stone.algorithm.leetcode;

/**
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 */
public class _0494_TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWaysHelper(nums, 0, target, 0);
    }

    private int findTargetSumWaysHelper(int[] nums, int index, int target, int sum) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }
        int num = nums[index];
        return findTargetSumWaysHelper(nums, index + 1, target, sum + num) +
                findTargetSumWaysHelper(nums, index + 1, target, sum - num);
    }
}
