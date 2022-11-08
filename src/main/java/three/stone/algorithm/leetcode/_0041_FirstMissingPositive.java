package three.stone.algorithm.leetcode;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 */
public class _0041_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        // 把正整数x放到num[x - 1]的位置
        for (int i = 0; i < len; ) {
            int num = nums[i];
            if (num <= 0 || num > len) {
                i++;
            } else {
                int temp = nums[num - 1];
                if (temp == num) {
                    // 注意避免无限循环
                    i++;
                    continue;
                }
                nums[num - 1] = num;
                nums[i] = temp;
            }
        }

        // 第一个不满足nums[i] != i + 1的数就是答案
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }
}
