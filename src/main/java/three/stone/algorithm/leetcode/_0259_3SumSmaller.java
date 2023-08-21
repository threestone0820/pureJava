package three.stone.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an array arr[] of distinct integers of size N and a value sum,
 * the task is to find the count of triplets (i, j, k), having (i<j<k)
 * with the sum of (arr[i] + arr[j] + arr[k]) smaller than the given value sum.
 *
 *
 * Example 1:
 *
 *
 * Input: N = 4, sum = 2
 * arr[] = {-2, 0, 1, 3}
 * Output:  2
 * Explanation: Below are triplets with
 * sum less than 2 (-2, 0, 1) and (-2, 0, 3).
 */
public class _0259_3SumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                long first = nums[i], second = nums[j], third = nums[k];
                long curSum = first + second + third;
                if (curSum < target) {
                    result += (k - j);
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

}
