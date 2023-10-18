package three.stone.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0290_WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] allWords = s.split(" ");
        if (allWords.length != pattern.length()) {
            return false;
        }

        Map<String, Character> map1 = new HashMap<>();
        Map<Character, String> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = allWords[i];
            Character mappedCh = map1.get(word);
            String mappedWord = map2.get(ch);
            if ((mappedCh == null) != (mappedWord == null)) {
                return false;
            }

            if (mappedCh == null && mappedWord == null) {
                map1.put(word, ch);
                map2.put(ch, word);
            } else if (mappedCh != ch || !mappedWord.equals(word)) {
                return false;
            }
        }
        return true;
    }

}
