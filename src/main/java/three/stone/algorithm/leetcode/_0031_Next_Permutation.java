package three.stone.algorithm.leetcode;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class _0031_Next_Permutation {
    public static void nextPermutation(int[] nums) {
        int length = nums.length;
        for (int i = length - 1; i > 0 ; i--) {
            // find the first one with index i that satisfy num[i-1] < num[i].
            // So, elements from num[i] to num[n-1] is reversely sorted.
            if (nums[i] > nums[i - 1]) {
                //swap it with the smallest number between num[i,n-1] that is larger than num[i-1].
                // For example, original number is 121543321, we want to swap the '1' at position 2 with '2' at position 7.
                int j = length - 1;
                while (nums[j] <= nums[i - 1]) {
                    j--;
                }
                int temp = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = temp;

                reverse(nums, i, length - 1);
                return;
            }
        }

        reverse(nums, 0, length - 1);
    }

    private static void reverse(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 4, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
