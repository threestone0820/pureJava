package three.stone.algorithm.modified_binary_search;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >> 1;
            int value = nums[mid];
            if (value >= nums[low] && value > nums[high]) {
                // 左边
                low = mid + 1;
            } else {
                // 右边，但mid可能是最小值，所以不能-1
                high = mid;
            }
        }
        return nums[low];
    }
}
