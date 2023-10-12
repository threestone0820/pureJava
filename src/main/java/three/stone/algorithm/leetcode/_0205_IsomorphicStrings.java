package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
            map1[s.charAt(i)] = i;
            map2[t.charAt(i)] = i;
        }
        return true;
    }
}
