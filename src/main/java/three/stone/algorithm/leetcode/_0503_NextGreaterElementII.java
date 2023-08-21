package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class _0503_NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();

        // 环形数组，常用套路就是将数组长度翻倍，
        // 最简单的实现方式当然可以把这个双倍长度的数组构造出来但是，
        // 我们也可以不构造新数组，而是利用循环数组的技巧来模拟数组长度翻倍的效果
        for (int i = 2 * n - 1; i >= 0; i--) {

            // 单调栈解题
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            // i 处于[n, 2n - 1] 只是为了构造栈，不需要写result
            // i 处于[0, n - 1] 时我们写result
            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[i % n]);
        }
        return result;
    }
}
