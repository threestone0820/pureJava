package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 2:abc 3:def 4:ghi 5:jkl 6:mno 7:pqrs 8:tuv 9:wxyz
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class _0017_Letter_Combinations_of_A_Phone_Number {
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        List<String> letters = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            letters.add(map.get(c));
        }

        backtrace(result, letters, 0, new StringBuilder());
        return result;
    }

    private void backtrace(List<String> result, List<String> letters, int start, StringBuilder builder) {
        if (start == letters.size()) {
            result.add(builder.toString());
            return;
        }

        char[] chars = letters.get(start).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            builder.append(chars[i]);
            backtrace(result, letters, start + 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
