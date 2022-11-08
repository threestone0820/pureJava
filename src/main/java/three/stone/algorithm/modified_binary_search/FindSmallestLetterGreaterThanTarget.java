package three.stone.algorithm.modified_binary_search;

/**
 * Given a characters array letters that is sorted in non-decreasing order
 * and a character target, return the smallest character in the array that is larger than target.
 *
 * Note that the letters wrap around.
 *
 * For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 *
 * Example 1:
 *
 * Input: letters = ["c","f","j"], target = "a"
 * Output: "c"
 * Example 2:
 *
 * Input: letters = ["c","f","j"], target = "c"
 * Output: "f"
 * Example 3:
 *
 * Input: letters = ["c","f","j"], target = "d"
 * Output: "f"
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        // 注意： >=
        if (target >= letters[letters.length - 1] || target < letters[0]) {
            return letters[0];
        }

        int low = 0, high = letters.length - 1;
        //注意这里是<，等于的时候我们就已经拿到结果了
        while (low < high) {
            int mid = (low + high) >> 1;
            char value = letters[mid];
            if (value <= target) {
                low = mid + 1;
            } else {
                // 这里high = mid，而不是mid - 1
                high = mid;
            }
        }
        return letters[low];
    }
}
