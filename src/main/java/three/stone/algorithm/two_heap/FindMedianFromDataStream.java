package three.stone.algorithm.two_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value and
 * the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 */
public class FindMedianFromDataStream {
    class MedianFinder {
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        public MedianFinder() {

        }

        public void addNum(int num) {
            int minHeapSize = minHeap.size();
            int maxHeapSize = maxHeap.size();
            if (minHeapSize == maxHeapSize) {
                maxHeap.offer(num);
            } else if (minHeapSize < maxHeapSize) {
                minHeap.offer(num);
            }

            Integer minVal = minHeap.peek();
            Integer maxVal = maxHeap.peek();
            if (minVal != null && maxVal != null && minVal < maxVal) {
                minHeap.poll();
                maxHeap.poll();
                minHeap.offer(maxVal);
                maxHeap.offer(minVal);
            }
        }

        public double findMedian() {
            int minHeapSize = minHeap.size();
            int maxHeapSize = maxHeap.size();
            if (minHeapSize == maxHeapSize) {
                return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
            } else {
                return (double) maxHeap.peek();
            }
        }
    }
}
