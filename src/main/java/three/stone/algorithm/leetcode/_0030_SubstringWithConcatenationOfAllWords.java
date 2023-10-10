package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0030_SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        int len = s.length(), wordLen = words[0].length(), wordNum = words.length;
        for (int i = 0; i <= len - wordNum * wordLen; i++) {
            if (isConcatenation(s, i, wordLen, wordNum, counter)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isConcatenation(String s, int start, int wordLen, int wordNum, Map<String, Integer> counter) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < wordNum; i++) {
            String sub = s.substring(start, start + wordLen);
            start += wordLen;
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }
        return map.equals(counter);
    }
}
