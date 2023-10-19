package three.stone.algorithm.leetcode;

public class _0198_HouseRobber {
    public int rob(int[] nums) {
        int rob = nums[0], notRob = 0, max = rob;
        for (int i = 1; i < nums.length; i++) {
            int curRob = notRob + nums[i];
            int curNotRob = Math.max(rob, notRob);
            max = Math.max(curRob, curNotRob);
            rob = curRob;
            notRob = curNotRob;
        }
        return max;
    }
}
