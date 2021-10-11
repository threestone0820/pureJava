package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * Example 1:
 *
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums = [1,1]
 * Output: 1
 * Example 4:
 *
 * Input: nums = [1,1,2]
 * Output: 1
 */
public class _0287_Find_The_Duplicate_Number {
    // 注意：n+1个数，且每个整数的范围是[1,n]，所以每个整数都可以作为数组的下标
    // 和循环链表类似，遍历后最终形成一个环，而重复的那个数处在环的入口位置
    // 注意，遍历并不一定会遍历所有的数组元素
    public int findDuplicate(int[] nums) {
        int i = 0, j = 0;
        do {
            i = nums[i];
            j = nums[nums[j]];
        } while (i != j);

        i = 0;
        do {
            i = nums[i];
            j = nums[j];
        } while (i != j);
        return i;
    }
}
