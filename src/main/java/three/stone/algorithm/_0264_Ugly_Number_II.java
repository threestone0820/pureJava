package three.stone.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 * Example 1:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1690
 */
public class _0264_Ugly_Number_II {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int[] nums = new int[n];
        nums[0] = 1;
        int m2 = 0, m3 = 0, m5 = 0;
        for (int i = 1; i < n; ++i) {
            nums[i] = Math.min(nums[m2] * 2, Math.min(nums[m3] * 3, nums[m5] * 5));
            if (nums[i] == nums[m2] * 2) {
                m2++;
            }
            if (nums[i] == nums[m3] * 3) {
                m3++;
            }
            if (nums[i] == nums[m5] * 5) {
                m5++;
            }
        }

        return nums[n - 1];
    }
}
