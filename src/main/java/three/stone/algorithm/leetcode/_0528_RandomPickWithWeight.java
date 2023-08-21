package three.stone.algorithm.leetcode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range
 * [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%),
 * and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * 图：
 * |-|---|
 */
public class _0528_RandomPickWithWeight {
    class Solution {
        // 用前缀和数组解决此问题，可以画个图
        private int[] preSum;
        private int max;
        private int len;

        public Solution(int[] w) {
            preSum = new int[w.length];
            preSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                preSum[i] = preSum[i - 1] + w[i];
            }
            len = preSum.length;
            max = preSum[len - 1];
        }

        public int pickIndex() {
            int target = ThreadLocalRandom.current().nextInt(max);
            int low = 0, high = len - 1;

            // 二分查找刚好比target大的那个数
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int num = preSum[mid];
                if (num == target) {
                    // 找到这个值了，那下一个元素即比它大
                    return mid + 1;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 找不到某个数target时，low下标的位置即比target大
            return low;
        }
    }
}
