package three.stone.algorithm.leetcode;

public class _0392_IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }

        int i = 0, j = 0;
        while (i != s.length()) {
            char c = s.charAt(i);
            while (j < t.length() && t.charAt(j) != c) {
                j++;
            }
            if (j == t.length()) {
                break;
            } else {
                i++;
                j++;
            }
        }
        return i == s.length();
    }
}
