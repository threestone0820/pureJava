package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0219_ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> otherIndex = map.get(num);
            if (otherIndex == null) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(num, list);
            } else {
                for (Integer index : otherIndex) {
                    if (Math.abs(index - i) <= k) {
                        return true;
                    }
                }
                otherIndex.add(i);
            }
        }
        return false;
    }
}
