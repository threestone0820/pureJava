package three.stone.algorithm.cyclic_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n]
 * and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [4,-3,-2,-7,8,2,-3,-1]
 * Output: [2,3]
 */
public class Find_All_Duplicates_in_an_Array {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        cyclicSort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                result.add(nums[i]);
            }
        }

        return result;
    }

    private void cyclicSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[j] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            } else {
                i++;
            }
        }
    }
}
