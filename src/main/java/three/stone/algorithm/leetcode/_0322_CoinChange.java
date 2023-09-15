package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class _0322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 1; i <= amount; i++) {
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin == i) {
                    result = 1;
                } else if (coin < i) {
                    if (dp.containsKey(i - coin) && dp.get(i - coin) != -1) {
                        result = Math.min(result, dp.get(i - coin) + 1);
                    }
                }
            }
            dp.put(i, result == Integer.MAX_VALUE ? -1 : result);
        }
        return dp.getOrDefault(amount, -1);
    }

}
