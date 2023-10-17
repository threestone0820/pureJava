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
        int left = findIndex(nums, target, true);
        int right = findIndex(nums, target, false);
        result[0] = left;
        result[1] = right;
        return result;
    }

    private int findIndex(int[] nums, int target, boolean findLeft) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            if (low == high) {
                return nums[low] == target ? low : -1;
            }
            int mid = low + (high - low) / 2;
            int num = nums[mid];
            if (num == target) {
                if (findLeft) {
                    // 两个元素时，下标能移动
                    high = mid;
                } else {
                    // 注意这里：二分查找时，确保数组索引能够移动
                    // 主要考虑两个元素的情况
                    // 两个元素时，下标不能移动，所以需要特殊处理一下
                    if (low + 1 == high) {
                        return nums[high] == target ? high : low;
                    }
                    low = mid;
                }
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
