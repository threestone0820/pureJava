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
    private Map<Integer, Integer> dp = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp.containsKey(amount)) {
            return dp.get(amount);
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subResult = coinChange(coins, amount - coin);
            if (subResult != -1) {
                min = Math.min(min, 1 + subResult);
            }
        }
        if (min != Integer.MAX_VALUE) {
            dp.put(amount, min);
            return min;
        } else {
            return -1;
        }
    }
}
