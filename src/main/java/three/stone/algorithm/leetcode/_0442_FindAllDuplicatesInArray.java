package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n]
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * Each element in nums appears once or twice.
 */
public class _0442_FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        cyclicSort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    private void cyclicSort(int[] nums) {
        int len = nums.length, i = 0;
        while (i < len) {
            int num = nums[i];
            if (num == i + 1 || nums[i] == nums[num - 1]) {
                i++;
            } else {
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
            }
        }
    }
}
