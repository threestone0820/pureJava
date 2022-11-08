package three.stone.algorithm.two_pointer;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class Sort_Colors {
    public void sortColors(int[] nums) {
        int low = 0, high = nums.length - 1;
        for (int i = low; i <= high; ) {
            int num = nums[i];
            if (num == 0) {
                // 这里i能++
                nums[i++] = nums[low];
                nums[low++] = 0;
            } else if (num == 2) {
                // 这里i不能++，nums[i] 有可能是任何值
                nums[i] = nums[high];
                nums[high--] = 2;
            } else {
                i++;
            }
        }
    }
}
