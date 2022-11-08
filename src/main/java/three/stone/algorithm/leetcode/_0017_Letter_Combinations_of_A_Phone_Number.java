package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * 2:abc 3:def 4:ghi 5:jkl 6:mno 7:pqrs 8:tuv 9:wxyz
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
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
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});
        char[] digitArr = digits.toCharArray();
        backtrace(map, result, new StringBuilder(), digitArr, 0);
        return result;
    }

    private void backtrace(Map<Character, char[]> map, List<String> result, StringBuilder builder, char[] digitArr, int index) {
        if (index == digitArr.length) {
            result.add(builder.toString());
            return;
        }

        char[] chars = map.get(digitArr[index]);
        for (char ch : chars) {
            builder.append(ch);
            backtrace(map, result, builder, digitArr, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
