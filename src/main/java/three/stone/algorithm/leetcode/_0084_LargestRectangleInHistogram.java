package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 */
public class _0084_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            // 加个哨兵，方便处理
            int height = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > height) {
                Integer index = stack.pop();
                int h = heights[index];
                // 关键代码，宽度的处理：宽度不能为 i - index
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                result = Math.max(result, h * w);
            }
            stack.push(i);
        }
        return result;
    }
}
