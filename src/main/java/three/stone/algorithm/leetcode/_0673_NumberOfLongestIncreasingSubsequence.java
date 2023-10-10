package three.stone.algorithm.leetcode;

public class _0673_NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] num = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            num[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        num[i] = num[j];
                    } else if (len[j] + 1 == len[i]) {
                        num[i] += num[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == maxLen) {
                result += num[i];
            }
        }
        return result;
    }
}
