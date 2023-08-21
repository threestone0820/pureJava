package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
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

    public int findDuplicateII(int[] nums) {
        cyclicSort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return 0;
    }

    private void cyclicSort(int[] nums) {
        int i = 0, len = nums.length;
        while (i < len) {
            int j = nums[i] - 1;
            if (nums[j] != nums[i]) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
    }
}
