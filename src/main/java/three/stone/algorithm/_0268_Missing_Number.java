package three.stone.algorithm;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 * Example 4:
 * <p>
 * Input: nums = [0]
 * Output: 1
 * Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 1 is the missing number in the range since it does not appear in nums.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 */
public class _0268_Missing_Number {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ) {
            int num = nums[i];
            if (num == n || num == -1) {
                i++;
            } else if (num == i) {
                // 注意这个情况：下标和值恰好相等，此时不需要交换值了，需要i++，否则会出现无限循环
                nums[i] = -1;
                i++;
            } else {
                int temp = nums[num];
                nums[num] = -1;
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != -1) {
                return i;
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
