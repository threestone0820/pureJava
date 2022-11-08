package three.stone.algorithm.topk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers arr and an integer k.
 * Find the least number of unique integers after removing exactly k elements.
 * Example 1:
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 */
public class LeastNumberOfUniqueIntegers {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.merge(i, 1, (oldValue, unused) -> oldValue + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        int count = 0;
        for (Integer num : list) {
            if (num <= k) {
                count++;
                k = k - num;
            } else {
                break;
            }
        }

        return list.size() - count;
    }
}
