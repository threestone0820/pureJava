package three.stone.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class _0015_3Sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 先排序，然后通过这种方式来跳过重复的数字，可以保证某个数字只处理过一次
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = -nums[i];

                int low = i + 1, high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] == target) {
                        int second = nums[low];
                        int third = nums[high];
                        result.add(Arrays.asList(nums[i], second, third));
                        while (low < high && second == nums[low]) {
                            low++;
                        }
                        while (low < high && third == nums[high]) {
                            high--;
                        }
                    } else if (nums[low] + nums[high] < target) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}
