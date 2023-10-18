package three.stone.algorithm.leetcode;

public class _1220_CountVowelsPermutation {
    int MOD = 1_000_000_007;
    public int countVowelPermutation(int n) {
        int[][] dp = new int[20001][26];
        int result = 0;
        result = (result + helper(n, 1, 'a', dp)) % MOD;
        result = (result + helper(n, 1, 'e', dp)) % MOD;
        result = (result + helper(n, 1, 'i', dp)) % MOD;
        result = (result + helper(n, 1, 'o', dp)) % MOD;
        result = (result + helper(n, 1, 'u', dp)) % MOD;
        return result;
    }

    private int helper(int n, int step, char c, int[][] dp) {
        if (step == n) {
            return 1;
        }
        if (dp[step][c - 'a'] != 0) {
            return dp[step][c - 'a'];
        }

        int result = 0;
        if (c == 'a') {
            result = (result + helper(n, step + 1, 'e', dp)) % MOD;
        } else if (c == 'e') {
            result = (result + helper(n, step + 1, 'a', dp)) % MOD;
            result = (result + helper(n, step + 1, 'i', dp)) % MOD;
        } else if (c == 'i') {
            result = (result + helper(n, step + 1, 'a', dp)) % MOD;
            result = (result + helper(n, step + 1, 'e', dp)) % MOD;
            result = (result + helper(n, step + 1, 'o', dp)) % MOD;
            result = (result + helper(n, step + 1, 'u', dp)) % MOD;
        } else if (c == 'o') {
            result = (result + helper(n, step + 1, 'i', dp)) % MOD;
            result = (result + helper(n, step + 1, 'u', dp)) % MOD;
        } else if (c == 'u') {
            result = (result + helper(n, step + 1, 'a', dp)) % MOD;
        }
        dp[step][c - 'a'] = result;
        return result;
    }
}
