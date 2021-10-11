package three.stone.algorithm.leetcode;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 */
public class _0509_Fibonacci_Number {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int slow = 0, quick = 1;
        for (int i = 2; i <= n; i++) {
            int temp = slow + quick;
            slow = quick;
            quick = temp;
        }

        return quick;
    }
}
