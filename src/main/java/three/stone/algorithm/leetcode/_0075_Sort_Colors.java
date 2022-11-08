package three.stone.algorithm.leetcode;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class _0075_Sort_Colors {
    public void sortColors(int[] nums) {
        int low = 0, high = nums.length - 1;
        for (int i = low; i <= high; ) {
            int num = nums[i];
            /**
             * 注意：在num==0或1时，i++了， num==2时，i不能++
             */
            if (num == 0) {
                nums[i++] = nums[low];
                nums[low++] = 0;
            } else if (num == 2) {
                nums[i] = nums[high];
                nums[high--] = 2;
            } else {
                i++;
            }
        }
    }
}
