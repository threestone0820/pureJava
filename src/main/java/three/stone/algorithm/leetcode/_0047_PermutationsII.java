package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 */
public class _0047_PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> tempCounter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
            tempCounter.put(num, 0);
        }
        backtrace(result, new ArrayList<>(), nums, counter, tempCounter);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> temp, int[] nums, Map<Integer, Integer> counter, Map<Integer, Integer> tempCounter) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; ) {
                int num = nums[i];
                if (!tempCounter.get(num).equals(counter.get(num))) {
                    temp.add(num);
                    tempCounter.compute(num, (key, oldValue) -> oldValue + 1);
                    backtrace(result, temp, nums, counter, tempCounter);
                    Integer removed = temp.remove(temp.size() - 1);
                    tempCounter.compute(removed, (key, oldValue) -> oldValue - 1);
                }
                while (++i < nums.length && nums[i] == nums[i - 1]) {

                }
            }
        }
    }
}
