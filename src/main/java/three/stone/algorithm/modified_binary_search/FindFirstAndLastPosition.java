package three.stone.algorithm.modified_binary_search;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0 || target > nums[length - 1] || target < nums[0]) {
            return new int[]{-1, -1};
        }

        int low = 0, high = length - 1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            int value = nums[mid];
            if (value == target) {
                index = mid;
                break;
            } else if (value > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int left = index, right = index;
        while (left > 0 && nums[left-1] == target) {
            left--;
        }
        while (right < length - 1 && nums[right+1] == target) {
            right++;
        }
        return new int[]{left, right};
    }

    public int[] searchRangeII(int[] nums, int target) {
        int length = nums.length;
        if (length == 0 || target > nums[length - 1] || target < nums[0]) {
            return new int[]{-1, -1};
        }

        int low = 0, high = nums.length - 1, mid, left = -1, right = -1;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    left = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] < target) {
                low++;
            } else {
                high--;
            }
        }

        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    right = mid;
                    break;
                } else {
                    low++;
                }
            } else if (nums[mid] < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{left, right};
    }
}
