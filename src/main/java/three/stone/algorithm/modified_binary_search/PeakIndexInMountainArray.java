package three.stone.algorithm.modified_binary_search;

/**
 * Let's call an array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * Example 1:
 *
 * Input: arr = [0,1,0]
 * Output: 1
 * Example 2:
 *
 * Input: arr = [0,2,1,0]
 * Output: 1
 * Example 3:
 * Input: arr = [0,10,5,2]
 * Output: 1
 */
public class PeakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            int value = arr[mid];
            // [3 5 3 2 0]
            // 有可能mid == 0，但是不可能mid==length - 1，所以不用判断mid==length-1的case
            if (mid == 0) {
                low = mid + 1;
                continue;
            }

            if (value > arr[mid - 1] && value > arr[mid + 1]) {
                return mid;
            }
            if (value > arr[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
