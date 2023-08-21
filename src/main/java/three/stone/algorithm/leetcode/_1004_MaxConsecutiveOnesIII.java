package three.stone.algorithm.leetcode;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's
 * in the array if you can flip at most k 0's.
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 */
public class _1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int start = 0, stop = 0, result = 0, numberOfZero = 0;
        while (stop < nums.length) {
            if (nums[stop++] == 0) {
                numberOfZero++;
            }
            while (numberOfZero > k) {
                if (nums[start++] == 0) {
                    numberOfZero--;
                }
            }
            result = Math.max(result, stop - start);
        }
        return result;
    }
}
