package three.stone.algorithm.leetcode;

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 */
public class _0035_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        if (target > nums[length - 1]) {
            return length;
        }
        if (target < nums[0]) {
            return 0;
        }

        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (low == high) {
                break;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (target > nums[low]) {
            return low + 1;
        } else {
            return low;
        }
    }
}
