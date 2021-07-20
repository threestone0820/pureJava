package three.stone.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class _0131_Palindrome_Partitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        backtrace(result, tempList, s, 0);
        return result;
    }

    private static void backtrace(List<List<String>> result, List<String> tempList, String s, int start) {
        if (isPalindrome(tempList)) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < s.length(); i++) {
                tempList.add(String.valueOf(s.charAt(i)));
                backtrace(result, tempList, s, i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(List<String> chars) {
        if (chars.isEmpty()) {
            return false;
        }
        if (chars.size() == 1) {
            return true;
        }

        String[] array  = chars.toArray(new String[0]);
        int low = 0, high = array.length - 1;
        while (low < high) {
            if (!array[low++].equals(array[high--])) {
                return false;
            }
        }
        return true;
    }
}
