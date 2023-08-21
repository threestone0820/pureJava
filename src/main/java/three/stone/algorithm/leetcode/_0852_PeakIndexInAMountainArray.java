package three.stone.algorithm.leetcode;

/**
 * Given a mountain array arr, return the index i
 *
 * Input: arr = [0,2,1,0]
 * Output: 1
 *
 * Input: arr = [0,10,5,2]
 * Output: 1
 */
public class _0852_PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid == 0) {
                low = mid + 1;
                continue;
            }
            int value = arr[mid];
            if (value > arr[mid - 1] && value > arr[mid + 1]) {
                return mid;
            } else if (value < arr[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
