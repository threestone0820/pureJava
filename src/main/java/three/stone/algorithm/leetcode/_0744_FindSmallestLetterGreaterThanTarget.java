package three.stone.algorithm.leetcode;

/**
 * Example 2:
 *
 * Input: letters = ["c","f","j"], target = "c"
 * Output: "f"
 * Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.
 * Example 3:
 *
 * Input: letters = ["x","x","y","y"], target = "z"
 * Output: "x"
 * Explanation: There are no characters in letters that is lexicographically
 * greater than 'z' so we return letters[0].
 */
public class _0744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length, low = 0, high = length - 1;
        if (target > letters[length - 1]) {
            return letters[0];
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            char ch = letters[mid];
            if (target >= ch) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return letters[low];
    }
}
