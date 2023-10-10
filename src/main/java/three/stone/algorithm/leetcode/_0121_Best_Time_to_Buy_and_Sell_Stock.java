package three.stone.algorithm.leetcode;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 */
public class _0121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, localProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            localProfit = Math.max(prices[i] - prices[i - 1], localProfit + prices[i] - prices[i - 1]);
            if (localProfit > maxProfit) {
                maxProfit = localProfit;
            }
        }
        return maxProfit;
    }
}
