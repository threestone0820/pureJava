package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _0169_MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.merge(num, 1, (oldValue, unused) -> oldValue + 1);
            if (count > nums.length / 2) {
                return num;
            }
        }
        return 0;
    }
}
