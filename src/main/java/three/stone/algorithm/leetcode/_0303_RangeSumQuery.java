package three.stone.algorithm.leetcode;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 */
public class _0303_RangeSumQuery {
    class NumArray {
        private int[] nums;
        /**
         * 前缀和技巧，避免重复计算
         * 前缀和主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和
         */
        private int[] preSumNums;

        public NumArray(int[] nums) {
            this.nums = nums;
            this.preSumNums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    preSumNums[0] = nums[0];
                } else {
                    preSumNums[i] = nums[i] + preSumNums[i - 1];
                }
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return preSumNums[right];
            }
            return preSumNums[right] - preSumNums[left - 1];
        }
    }
}
