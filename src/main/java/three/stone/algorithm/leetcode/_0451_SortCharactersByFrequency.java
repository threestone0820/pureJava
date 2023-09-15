package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Input: s = "tree"
 * Output: "eert"
 */
public class _0451_SortCharactersByFrequency {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> counter = new HashMap<>();
        for (char ch : chars) {
            counter.merge(ch, 1, (oldValue, unused) -> oldValue + 1);
        }
        StringBuilder builder = new StringBuilder(s.length());
        counter.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<Character, Integer> e) -> e.getValue()).reversed())
                .forEach(entry -> {
                    for (int i = 0; i < entry.getValue(); i++) {
                        builder.append(entry.getKey());
                    }
                });

        return builder.toString();
    }
}
