package three.stone.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1 + 81 = 82
 * 64 + 4 = 68
 * 36 + 64 = 100
 * 1 + 0 + 0 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 */
public class _0202_Happy_Number {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        // 如果add返回true，表明集合中当前没有这样的值
        // 同样，Map.put方法： return the previous value associated with key, or
        // null if there was no mapping for key
        while (set.add(n)) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            if (sum == 1) {
                return true;
            }
            n = sum;
        }
        return false;
    }
}
