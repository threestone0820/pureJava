package three.stone.algorithm.leetcode;

public class _0080_RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int n = nums.length, i = 0, k = 0;
        while (i < n) {
            int value = nums[i], j = i + 1;
            while (j < n && nums[j] == value) {
                j++;
            }
            nums[k++] = value;
            if (j > i + 1) {
                nums[k++] = value;
            }
            i = j;
        }
        return k;
    }
}
