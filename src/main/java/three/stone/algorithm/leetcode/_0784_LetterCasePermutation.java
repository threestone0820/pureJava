package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, you can transform every letter individually to be lowercase or uppercase
 * to create another string.
 *
 * Return a list of all possible strings we could create. Return the output in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 */
public class _0784_LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        char[] charArray = s.toCharArray();
        List<String> result = new ArrayList<>();
        dfs(result, charArray, 0);
        return result;
    }

    private void dfs(List<String> result, char[] charArray, int index) {
        if (index == charArray.length) {
            result.add(new String(charArray));
        } else {
            char ch = charArray[index];
            if (ch >= '0' && ch <= '9') {
                dfs(result, charArray, index + 1);
                return;
            }
            charArray[index] = Character.toUpperCase(ch);
            dfs(result, charArray, index + 1);
            charArray[index] = Character.toLowerCase(ch);
            dfs(result, charArray, index + 1);
        }
    }

    // BFS
    public List<String> letterCasePermutationII(String s) {
        LinkedList<String> queue = new LinkedList<>();
        if (s == null || s.isEmpty()) {
            return queue;
        }
        queue.offer(s);
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i])) {
                continue;
            }
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                char[] charArray1 = queue.poll().toCharArray();
                charArray1[i] = Character.toLowerCase(charArray1[i]);
                queue.offer(new String(charArray1));
                charArray1[i] = Character.toUpperCase(charArray1[i]);
                queue.offer(new String(charArray1));
            }
        }
        return queue;
    }
}
