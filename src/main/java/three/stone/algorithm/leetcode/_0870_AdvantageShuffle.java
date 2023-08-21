package three.stone.algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 both of the same length.
 * The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].
 *
 * Return any permutation of nums1 that maximizes its advantage with respect to nums2.
 * Example 1:
 *
 * Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 *
 * Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * Output: [24,32,8,12]
 */
public class _0870_AdvantageShuffle {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;
        Arrays.sort(nums1);
        int[] result = new int[len];
        // 用int[2]的数组，分别记数值和下标
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < len; i++) {
            maxHeap.offer(new int[]{nums2[i], i});
        }

        int left = 0, right = len - 1;
        while (!maxHeap.isEmpty()) {
            int[] max = maxHeap.poll();
            if (nums1[right] > max[0]) {
                result[max[1]] = nums1[right--];
            } else {
                // 拿最小的顶上
                result[max[1]] = nums1[left++];
            }
        }

        return result;
    }
}
