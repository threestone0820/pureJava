package three.stone.algorithm.modified_binary_search;

/**
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.
 * If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly.
 * You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * Example 1:
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 *
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 */

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
interface MountainArray {
    public int get(int index);

    public int length();
}

class MountainArrayImpl implements MountainArray {
    private int[] arr = new int[]{1, 5, 2};
    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return 3;
    }
}

public class FindInMountainArray {
    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int peekIndex = peekIndex(mountainArr);
        int leftIndex = binarySearch(target, mountainArr, 0, peekIndex, true);
        if (leftIndex != -1) {
            return leftIndex;
        }

        return binarySearch(target, mountainArr, peekIndex, mountainArr.length() - 1, false);
    }

    private static int peekIndex(MountainArray arr) {
        int low = 0, high = arr.length() - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (mid == 0) {
                low = mid + 1;
                continue;
            }
            int value = arr.get(mid);
            int left = arr.get(mid - 1);
            int right = arr.get(mid + 1);
            if (value > left && value > right) {
                return mid;
            }
            if (value > left) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static int binarySearch(int target, MountainArray mountainArr, int low, int high, boolean left) {
        while (low <= high) {
            int mid = (low + high) >> 1;
            int value = mountainArr.get(mid);
            if (target == value) {
                return mid;
            } else if (target < value) {
                if (left) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (left) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        findInMountainArray(2, new MountainArrayImpl());
    }

}
