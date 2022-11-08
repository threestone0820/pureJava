package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 *
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 */
public class _0704_BinarySearch {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
