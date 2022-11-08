package three.stone.algorithm.leetcode;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.ArrayList;
import java.util.List;

/**
 * Design your implementation of the circular queue.
 * The circular queue is a linear data structure in which the operations are performed based on FIFO
 * (First In First Out) principle and the last position is connected back to the first position to make a circle.
 * It is also called "Ring Buffer".
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 */
public class _0622_DesignCircularQueue {
    final int[] a;
    int front = 0, rear = -1, len = 0;

    public _0622_DesignCircularQueue(int k) { a = new int[k];}

    public boolean enQueue(int val) {
        if (!isFull()) {
            rear = (rear + 1) % a.length;
            a[rear] = val;
            len++;
            return true;
        } else return false;
    }

    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % a.length;
            len--;
            return true;
        } else return false;
    }

    public int Front() { return isEmpty() ? -1 : a[front];}

    public int Rear() {return isEmpty() ? -1 : a[rear];}

    public boolean isEmpty() { return len == 0;}

    public boolean isFull() { return len == a.length;}
}
