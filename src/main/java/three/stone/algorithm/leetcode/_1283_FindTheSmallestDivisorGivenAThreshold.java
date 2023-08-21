package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor
 * , divide all the array by it, and sum the division's result. Find the smallest divisor
 * such that the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than or equal to that element.
 * (For example: 7/3 = 3 and 10/2 = 5).
 *
 * The test cases are generated so that there will be an answer.
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 *
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 */
public class _1283_FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        // 小心，最小值可以为1，不是nums中的最小值
        int left = 1, right = nums[0];
        for (int num : nums) {
            right = Math.max(right, num);
        }

        // 二分查找
        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = 0;
            for (int num : nums) {
                // sum += (num + mid - 1) / mid
                sum += num / mid;
                if (num % mid != 0) {
                    sum += 1;
                }
            }
            if (sum > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
