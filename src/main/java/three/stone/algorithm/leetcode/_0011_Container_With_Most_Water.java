package three.stone.algorithm.leetcode;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].`
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class _0011_Container_With_Most_Water {
    public int maxArea(int[] height) {
        int low = 0, high = height.length - 1;
        int area = 0;
        while (low < high) {
            int w = high - low;
            int h = Math.min(height[low], height[high]);
            area = Math.max(area, w * h);

            /**
             * Assume that height[left] <= height[right] now and we left shift the right pointer. It will comes two following situations:
             *
             * 1. height[new_right] >= height[original_right].
             *      Obviously, the height of the container is still height[left], but the width is smaller.
             * 2. height[new_right] < height[original_right].
             *      In this case, the width of the container is smaller and the height is min(height[left],height[new_right]) <= height[left]=original height.
             *
             * The both situations cannot lead to a lager area. So we just need to right shift the left pointer.
             */
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return area;
    }
}
