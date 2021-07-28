package three.stone.algorithm;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * Example 1:
 * <p>
 * Input: x = 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: x = -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: x = 120
 * Output: 21
 * Example 4:
 * <p>
 * Input: x = 0
 * Output: 0
 */
public class _0007_Reverse_Integer {
    public static int reverse(int x) {
        int result = 0;
        // x可能是负数，所以不能用 x > 0 来判断
        while (x != 0) {
            int remain = x % 10;
            int newResult = result * 10 + remain;
            // trick：通过这种方式判读是否溢出
            // 注意： 不能用 ((newResult - remain) != result * 10)
            // 因为这样的话 result * 10 也溢出了，只能用减法、除法
            if ((newResult - remain) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
