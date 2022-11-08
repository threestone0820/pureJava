package three.stone.algorithm.cyclic_sort;

/**
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 */
public class Find_the_Duplicate_Number {
    public int findDuplicate(int[] nums) {
        // 方法1, 快慢指针法，可以不用修改原数组
        int i = nums[0], j = nums[0];
        do {
            i = nums[i];
            j = nums[nums[j]];
        } while (i != j);

        i = nums[0];
        while (i != j) {
            i = nums[i];
            j = nums[j];
        }
        return i;
    }

    public int findDuplicateII(int[] nums) {
        // 方法2，用cyclic sort，然后nums[nums.length - 1] 即是重复的数字
        for (int i = 0; i < nums.length; ) {
            int index = nums[i] - 1;
            if (nums[index] != nums[i]) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        return nums[nums.length - 1];
    }
}
