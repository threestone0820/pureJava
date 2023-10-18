package three.stone.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

public class _0066_PlusOne {
    public int[] plusOne(int[] digits) {
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 1, n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            carry = sum / 10;
            result.addFirst(sum % 10);
        }
        if (carry == 1) {
            result.addFirst(carry);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
