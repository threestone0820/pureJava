package three.stone.algorithm.cyclic_sort;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 */
public class First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int index = nums[i] - 1;
            //算法精髓： 非正数和超过nums.length的数都不用理会
            if (index >= 0 && index < nums.length && nums[index] != nums[i]) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        i = 0;
        while (i < nums.length) {
            if (i != nums[i] - 1) {
                return i + 1;
            }
            i++;
        }
        // 所有的都满足，返回下一个正整数
        return nums.length + 1;
    }
}
