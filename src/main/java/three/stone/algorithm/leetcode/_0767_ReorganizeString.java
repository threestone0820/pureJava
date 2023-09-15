package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent
 * characters are not the same.
 *
 * Input: s = "aab"
 * Output: "aba"
 *
 * Input: s = "aaab"
 * Output: ""
 */
public class _0767_ReorganizeString {
    public String reorganizeString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> counter = new HashMap<>();
        for (char ch : chars) {
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = counter.entrySet()
                .stream()
                .sorted(Comparator
                        .comparingInt((Map.Entry<Character, Integer> e) -> e.getValue())
                        .reversed())
                .collect(Collectors.toList());
        if (list.get(0).getValue() > (chars.length + 1) / 2) {
            return "";
        }
        int i = 0;
        for (Map.Entry<Character, Integer> entry : list) {
            Character ch = entry.getKey();
            Integer count = entry.getValue();
            for (int j = 0; j < count; j++) {
                chars[i] = ch;
                if (i + 2 >= chars.length) {
                    i = 1;
                } else {
                    i += 2;
                }
            }
        }
        return new String(chars);
    }
}
