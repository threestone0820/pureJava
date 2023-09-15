package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 */
public class _0001_Two_Sum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null) {
                result[0] = i;
                result[1] = j;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        return result;
    }
}
