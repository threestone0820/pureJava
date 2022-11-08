package three.stone.algorithm.leetcode;

import java.util.Stack;

/**
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
 * You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
 *
 * A person can see another person to their right in the queue if everybody in between is shorter than both of them.
 * More formally, the ith person can see the jth person
 * if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
 *
 * Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
 *
 * Input: heights = [10,6,8,5,11,9]
 * Output: [3,1,2,1,1,0]
 * Explanation:
 * Person 0 can see person 1, 2, and 4.
 * Person 1 can see person 2.
 * Person 2 can see person 3 and 4.
 * Person 3 can see person 4.
 * Person 4 can see person 5.
 * Person 5 can see no one since nobody is to the right of them.
 */
public class _1944_NumberOfVisiblePeopleInQueue {
    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] <= height) {
                // 此时应该pop，因为被挡住了，后面的人都看不到了
                result[stack.pop()]++;
            }
            if (!stack.isEmpty()) {
                // 此时应该peek后+1，因为代表前面遍历的人可以看到当前结点
                result[stack.peek()]++;
            }
            stack.push(i);
        }
        return result;
    }
}
