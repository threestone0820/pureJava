package three.stone.algorithm.two_pointer;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of
 * each number sorted in non-decreasing order.
 *
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 */
public class Squares_of_Sorted_Array {
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int i = 0, j = length - 1, index = length - 1;
        while (i <= j) {
            int left = nums[i] * nums[i];
            int right = nums[j] * nums[j];
            if (left > right) {
                result[index--] = left;
                i++;
            } else {
                result[index--] = right;
                j--;
            }

        }
        return result;
    }
}
