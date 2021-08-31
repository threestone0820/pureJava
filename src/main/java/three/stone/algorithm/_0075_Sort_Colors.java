package three.stone.algorithm;

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
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * Example 3:
 *
 * Input: nums = [0]
 * Output: [0]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is 0, 1, or 2.
 */
public class _0075_Sort_Colors {
    public void sortColors(int[] nums) {
        int low = 0, high = nums.length - 1;
        for (int i = low; i <= high; ) {
            int num = nums[i];
            /**
             * 注意：在num==0时，i++了， num==2时，i不能++
             * 这是因为：num==0时，交换后nums[i]之前的值一定是满足要求的0或1，可以跳过(也必须跳过)
             *         num==2时，交换后nums[i]的值可能是2，需要再检查一次
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
