package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1269_NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    int MOD = 1_000_000_007;
    public int numWays(int steps, int arrLen) {
        Map<String, Integer> dp = new HashMap<>();
        int result = 0;
        result = (result + helper(steps, steps - 1, 0, arrLen, dp)) % MOD;
        if (arrLen > 1) {
            result = (result + helper(steps, steps - 1, 1, arrLen, dp)) % MOD;
        }
        return result;
    }

    private int helper(int steps, int remainSteps, int curPos, int arrLen, Map<String, Integer> dp) {
        if (remainSteps == 0) {
            if (curPos == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        if (curPos > remainSteps) {
            return 0;
        }
        String key = curPos + "_" + remainSteps;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int result = 0;
        result = (result + helper(steps, remainSteps - 1, curPos, arrLen, dp)) % MOD;
        if (curPos > 0) {
            result = (result + helper(steps, remainSteps - 1, curPos - 1, arrLen, dp)) % MOD;
        }
        if (curPos < arrLen - 1) {
            result = (result + helper(steps, remainSteps - 1, curPos + 1, arrLen, dp)) % MOD;
        }

        dp.put(key, result);
        return result;
    }
}
