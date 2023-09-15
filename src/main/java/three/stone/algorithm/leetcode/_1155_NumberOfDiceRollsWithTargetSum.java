package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1155_NumberOfDiceRollsWithTargetSum {
    public int numRollsToTarget(int n, int k, int target) {
        if (n > target || target > n * k) {
            return 0;
        }
        Map<String, Integer> dp = new HashMap<>();
        helper(n, k, target, dp);
        return dp.get(n + ":" + target) % (1000000000 + 7);
    }

    private int helper(int n, int k, int target, Map<String, Integer> dp) {
        String key = n + ":" + target;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int result = 0;
        if (n == 0) {
            result = target > 0 ? 0 : 1;
        } else {
            for (int i = 1; i <= k; i++) {
                if (target - i >= 0) {
                    result = (result + helper(n - 1, k, target - i, dp)) % (1000000000 + 7);
                }
            }
        }
        dp.put(key, result);
        return result;
    }
}
