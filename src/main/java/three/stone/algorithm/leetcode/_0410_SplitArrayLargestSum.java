package three.stone.algorithm.leetcode;

/**
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays
 * such that the largest sum of any subarray is minimized.
 *
 * Return the minimized largest sum of the split.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 */
public class _0410_SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int left = nums[0], right = nums[0];
        // 注意左右边界的取值
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        // 二分法一个个的试
        while (left < right) {
            int mid = left + (right - left) / 2;
            int needSplit = 1, acc = 0;
            for (int num : nums) {
                // 单个元素已经超过了最大值，那mid肯定不满足
                // needSplit = k + 1是为了让下面的判断成立
                if (num > mid) {
                    needSplit = k + 1;
                    break;
                }
                if (acc + num > mid) {
                    acc = 0;
                    needSplit++;
                }
                acc += num;
            }

            if (needSplit > k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
