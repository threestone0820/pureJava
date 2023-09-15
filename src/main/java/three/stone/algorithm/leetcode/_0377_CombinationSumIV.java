package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * All the elements of nums are unique. 7, 8, 9 12
 */
public class _0377_CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        return help(nums, target, new HashMap<>());
    }

    private int help(int[] nums, int target, Map<Integer, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int result = 0;
        for (int num : nums) {
            if (target > num) {
                result += help(nums, target - num, map);
            }
            if (target == num) {
                result += 1;
            }
        }
        map.put(target, result);
        return result;
    }

    public int combinationSumII(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
