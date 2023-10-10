package three.stone.algorithm.leetcode;

import java.util.Arrays;

public class _1092_ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        String commonSubsequence = longestCommonSubsequence(str1, str2);
        int m = str1.length();
        int n = str2.length();
        int i = 0, j = 0;
        StringBuilder builder = new StringBuilder();
        for (char c : commonSubsequence.toCharArray()) {
            while (i < m && str1.charAt(i) != c) {
                builder.append(str1.charAt(i++));
            }
            while (j < n && str2.charAt(j) != c) {
                builder.append(str2.charAt(j++));
            }
            i++;
            j++;
            builder.append(c);
        }
        builder.append(str1.substring(i));
        builder.append(str2.substring(j));
        return builder.toString();
    }

    private String longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        String[][] dp = new String[m + 1][n + 1];
        for (String[] s : dp) {
            Arrays.fill(s, "");
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + str1.charAt(i);
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1].length() > dp[i + 1][j].length() ? dp[i][j + 1] : dp[i + 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
