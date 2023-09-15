package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Return the sum of all the unique elements of nums.
 * Input: nums = [1,2,3,2]
 * Output: 4
 *
 * Input: nums = [1,1,1,1,1]
 * Output: 0
 */
public class _1748_SumOfUniqueElements {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, (oldValue, unused) -> oldValue + 1);
        }

        return counter.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .sum();
    }
}
