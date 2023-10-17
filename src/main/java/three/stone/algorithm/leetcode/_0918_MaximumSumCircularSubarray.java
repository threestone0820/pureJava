package three.stone.algorithm.leetcode;

// 5 -3, 5, 5, -3,
public class _0918_MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int max = nums[0], localMax = 0;
        int min = nums[0], localMin = 0;
        int sum = 0;
        for (int num : nums) {
            localMax = Math.max(localMax + num, num);
            max = Math.max(max, localMax);
            localMin = Math.min(localMin + num, num);
            min = Math.min(min, localMin);
            sum += num;
        }
        return max > 0 ? Math.max(max, sum - min) : max;
    }
}
