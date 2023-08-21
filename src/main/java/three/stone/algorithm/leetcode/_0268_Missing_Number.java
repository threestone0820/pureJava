package three.stone.algorithm.leetcode;

/**
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Input: nums = [0]
 * Output: 1
 *
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 */
public class _0268_Missing_Number {
    public int missingNumber(int[] nums) {
        int n = nums.length, i = 0;
        while (i < n) {
            if (nums[i] == i || nums[i] == n) {
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        for (int j = 0; j < n; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return n;
    }

    // 使用异或： a^b^b =a, 0^a = a
    public int missingNumberII(int[] nums) {
        int n = nums.length, result = n;
        for (int i = 0; i < n; i++) {
            result = result ^ i ^ nums[i];
        }
        return result;
    }

}
