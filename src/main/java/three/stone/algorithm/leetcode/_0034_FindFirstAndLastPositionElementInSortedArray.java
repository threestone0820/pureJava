package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class _0034_FindFirstAndLastPositionElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int left = findLeft(nums, target);
        if (left == -1) {
            result[0] = -1;
            result[1] = -1;
        } else {
            result[0] = left;
            result[1] = findRight(nums, target);
        }
        return result;
    }

    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            } else if (num > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            } else if (num > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right == -1) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }


}
