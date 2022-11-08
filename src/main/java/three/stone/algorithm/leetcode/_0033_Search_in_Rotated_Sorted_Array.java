package three.stone.algorithm.leetcode;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Given the array nums after the rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is guaranteed to be rotated at some pivot.
 */
public class _0033_Search_in_Rotated_Sorted_Array {
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            int cur = nums[mid];
            if (cur == target) {
                return mid;
            }

            // 旋转数组：我们先用cur和左右边界比较，（正常数组的二分查找使用cur和target比较）
            // 来决定当前位置是处于左边的区间，还是处于右边的区间
            if (cur >= nums[low]) {
                // 左边的区间
                if (target > cur || target < nums[low]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // 右边的区间
                if (target < cur || target > nums[high]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
