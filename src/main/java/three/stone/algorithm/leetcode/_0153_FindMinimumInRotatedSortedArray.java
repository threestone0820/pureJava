package three.stone.algorithm.leetcode;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 */
public class _0153_FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        while (low <= high) {
            if (low == high) {
                break;
            }
            int mid = low + (high - low) / 2;
            int num = nums[mid];
            if (num > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
