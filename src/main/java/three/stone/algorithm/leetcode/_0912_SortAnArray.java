package three.stone.algorithm.leetcode;

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
 * and with the smallest space complexity possible.
 */
public class _0912_SortAnArray {

    // 快速排序
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int l, int h) {
        if (l >= h) {
            return;
        }
        int index = partition(nums, l, h);
        quickSort(nums, l, index - 1);
        quickSort(nums, index + 1, h);
    }

    private int partition(int[] nums, int l, int h) {
        int target = nums[l];
        while (l < h) {
            while (l < h && nums[h] >= target) {
                h--;
            }
            nums[l] = nums[h];
            while (l < h && nums[l] <= target) {
                l++;
            }
            nums[h] = nums[l];
        }
        nums[l] = target;
        return l;
    }

    // 归并排序
    public int[] sortArrayII(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, r);
    }

    private void merge(int[] nums, int l, int r) {
        int[] temp = new int[r - l + 1];
        int mid = l + (r - l) / 2;
        int p = l, q = mid + 1, i = 0;
        // 注意这里是 <= ，[l, mid], [mid + 1, r] 都是闭区间的
        while (p <= mid && q <= r) {
            temp[i++] = nums[p] < nums[q] ? nums[p++] : nums[q++];
        }
        while (p <= mid) {
            temp[i++] = nums[p++];
        }
        while (q <= r) {
            temp[i++] = nums[q++];
        }
        System.arraycopy(temp, 0, nums, l, temp.length);
    }
}
