package three.stone.algorithm.leetcode;

import java.util.Arrays;

/**
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i]. Each day,
 * we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in
 * all the packages on the conveyor belt being shipped within days days.
 *
 * Note that the cargo must be shipped in the order given.
 *
 * Example 2:
 *
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 */
public class _1011_CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        int min = 0, max = 0;
        // 神奇的二分查找，先找出答案的可能范围[min, max]，然后用二分查找一个个尝试
        // 注意二分查找的结束条件
        for (int weight : weights) {
            min = Math.max(min, weight);
            max += weight;
        }

        while (min < max) {
            // 坑：+ 和 >> 的运算级？ 直接用除法，或者加括号
            int capacity = min + (max - min) / 2;
            int curWeight = 0, needDays = 1;
            for (int i = 0; i < weights.length; i++ ) {
                if (curWeight + weights[i] > capacity) {
                    needDays += 1;
                    curWeight = 0;
                }
                curWeight += weights[i];
            }

            if (needDays > days) {
                min = capacity + 1;
            } else {
                max = capacity;
            }
        }
        return min;
    }

}
