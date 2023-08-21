package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * You are given an integer n and an array of unique integers blacklist.
 * Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist.
 * Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.
 *
 * Optimize your algorithm such that it minimizes the number of calls to the
 * built-in random function of your language.
 */
public class _0710_RandomPickWithBlacklist {
    /**
     * 1、如果想高效地，等概率地随机获取元素，就要使用数组作为底层容器。
     *
     * 2、如果要保持数组元素的紧凑性，可以把待删除元素换到最后，然后 pop 掉末尾的元素，这样时间复杂度就是 O(1) 了。
     *    当然，我们需要额外的哈希表记录值到索引的映射。
     */
    class Solution {
        Map<Integer, Integer> blacklistIndex = new HashMap<>();
        int boundary;

        public Solution(int n, int[] blacklist) {
            for (int i : blacklist) {
                // 大于n的排除
                if (i < n) {
                    blacklistIndex.put(i, i);
                }
            }

            int replacedIndex = n - 1;
            boundary = n - blacklistIndex.size();
            for (int i : blacklist) {
                // 注意这点：数组被一分为二，右边的不用处理，也不能处理，只需处理左边的
                if (i >= boundary) {
                    continue;
                }
                while (blacklistIndex.containsKey(replacedIndex)) {
                    replacedIndex--;
                }
                blacklistIndex.put(i, replacedIndex--);
            }
        }

        public int pick() {
            int next = ThreadLocalRandom.current().nextInt(boundary);
            Integer replacedIndex = blacklistIndex.get(next);
            if (replacedIndex == null) {
                return next;
            } else {
                return replacedIndex;
            }
        }
    }
}
