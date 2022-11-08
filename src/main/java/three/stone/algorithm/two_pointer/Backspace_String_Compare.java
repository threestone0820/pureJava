package three.stone.algorithm.two_pointer;

/**
 * Given two strings s and t, return true if they are equal when both are
 * typed into empty text editors. '#' means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 *
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 *
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 *
 * Input: s = "a##c", t = "#a#c"
 * Output: true
 * Explanation: Both s and t become "c".
 * Example 4:
 *
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 */
public class Backspace_String_Compare {
    public boolean backspaceCompare(String s, String t) {
        int index1 = s.length() - 1, index2 = t.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            index1 = findNextIndex(s, index1);
            index2 = findNextIndex(t, index2);

            if (index1 == -1 && index2 == -1) {
                return true;
            }

            if (index1 == -1 || index2 == -1) {
                return false;
            }

            if (s.charAt(index1) != t.charAt(index2)) {
                return false;
            }

            index1--;
            index2--;
        }

        return true;
    }

    // 从后往前找
    private int findNextIndex(String s, int index) {
        int numberOfBackspace = 0;
        while (index >= 0) {
            char ch = s.charAt(index);
            if (ch == '#') {
                numberOfBackspace++;
            } else if (numberOfBackspace > 0) {
                numberOfBackspace--;
            } else {
                return index;
            }
            index--;
        }
        return -1;
    }
}
