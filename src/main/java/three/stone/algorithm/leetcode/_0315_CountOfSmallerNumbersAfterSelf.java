package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return an integer array counts where
 * counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class _0315_CountOfSmallerNumbersAfterSelf {
    class Pair{
        int num;
        int index;

        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        int[] result = new int[nums.length];
        mergeSort(pairs, 0, pairs.length - 1, result);
        List<Integer> l = new ArrayList<>();
        for (int i : result) {
            l.add(i);
        }
        return l;
    }

    // 归并排序的特点：
    // merge前：左边 [l, mid], 右边[mid + 1, r]
    // merge时：如果选择了左边的某个结点，则右边[mid + 1, q] 的值都比这个结点值小
    private void mergeSort(Pair[] pairs, int l, int r, int[] result) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(pairs, l, mid, result);
        mergeSort(pairs, mid + 1, r, result);
        Pair[] temp = new Pair[r - l + 1];
        int p = l, q = mid + 1, i = 0;

        // 注意这个while循环的写法
        while (p <= mid || q <= r) {
            // 注意：pairs[p].num <= pairs[q].num，不能用小于
            // 即：相等时，要选择左边的结点，好好想想，这个很容易出错
            if (q > r || p <= mid && pairs[p].num <= pairs[q].num) {
                temp[i++] = pairs[p];
                result[pairs[p].index] += (q - (mid + 1));
                p++;
            } else {
                temp[i++] = pairs[q++];
            }
        }
        System.arraycopy(temp, 0, pairs, l, r - l + 1);
    }

}
