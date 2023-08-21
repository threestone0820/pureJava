package three.stone.algorithm.leetcode;

/**
 * Given a mountain array mountainArr, return the minimum index such that
 * mountainArr.get(index) == target. If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly.
 * You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 */
public class _1095_FindInMountainArray {
     interface MountainArray {
        int get(int index);

        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeakIndex(mountainArr);
        int index = binarySearch(target, mountainArr, 0, peak, true);
        if (index == -1) {
            index = binarySearch(target, mountainArr, peak, mountainArr.length() - 1, false);
        }
        return index;
    }

    private int findPeakIndex(MountainArray mountainArray) {
        int low = 0, high = mountainArray.length() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid == 0) {
                low = mid + 1;
                continue;
            }
            int value = mountainArray.get(mid);
            int left = mountainArray.get(mid - 1);
            int right = mountainArray.get(mid + 1);
            if (value > left && value > right) {
                return mid;
            }
            if (value < right) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int binarySearch(int target, MountainArray mountainArray, int low, int high, boolean asc) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int value = mountainArray.get(mid);
            if (value == target) {
                return mid;
            }
            if (asc) {
                if (value > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (value > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

}
