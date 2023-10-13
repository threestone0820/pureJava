package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0228_SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int i = 0;
        while (i < nums.length) {
            int j = i;
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) {
                j++;
            }
            result.add(i == j ? String.valueOf(nums[i]) : nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return result;
    }
}
