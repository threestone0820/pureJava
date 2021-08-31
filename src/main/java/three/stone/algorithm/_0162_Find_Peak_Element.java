package three.stone.algorithm;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */
public class _0162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            int num = nums[mid];
            boolean left = mid == 0 || num > nums[mid - 1];
            boolean right = mid == length - 1 || num > nums[mid + 1];
            if (left && right) {
                return mid;
            }

            // 注意，有可能！left和！right都为ture，所以要用else语句
            // 这样才能保证 high 和 low 在一次循环中只有一个值被更新
            if (!left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
