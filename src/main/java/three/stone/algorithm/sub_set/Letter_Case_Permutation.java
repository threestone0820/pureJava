package three.stone.algorithm.sub_set;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, we can transform every letter individually
 * to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * You can return the output in any order.
 *
 * Example 1:
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 * Example 3:
 *
 * Input: s = "12345"
 * Output: ["12345"]
 * Example 4:
 */
public class Letter_Case_Permutation {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        backtrace(result, chars, 0);
        return result;
    }

    private void backtrace(List<String> result, char[] chars, int index) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            if (Character.isLetter(chars[index])) {
                switchCharCase(chars, index);
                result.add(new String(chars));
            }
            return;
        }

        backtrace(result, chars, index + 1);
        if (Character.isLetter(chars[index])) {
            switchCharCase(chars, index);
            backtrace(result, chars, index + 1);
        }
    }

    private void switchCharCase(char[] chars, int index) {
        if (Character.isLowerCase(chars[index])) {
            chars[index] = Character.toUpperCase(chars[index]);
        } else {
            chars[index] = Character.toLowerCase(chars[index]);
        }
    }
}
