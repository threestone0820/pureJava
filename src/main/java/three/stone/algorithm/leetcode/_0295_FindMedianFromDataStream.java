package three.stone.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value,
 * and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * 1, 2, 3 , 10, 11, 12
 */
public class _0295_FindMedianFromDataStream {
    class MedianFinder {
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        public MedianFinder() {

        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return ((double)minHeap.peek() + (double)maxHeap.peek()) / 2;
            } else {
                return (double) maxHeap.peek();
            }
        }
    }
}
