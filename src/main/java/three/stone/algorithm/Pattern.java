package three.stone.algorithm;

import sun.reflect.Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sliding-window:
 * In many problems dealing with an array (or a LinkedList), we are asked to find or calculate
 * something among all the contiguous subarrays (or sublists) of a given size.
 * <p>
 * two-pointers:
 * In problems where we deal with sorted arrays (or LinkedLists) and need to find a set of elements
 * that fulfill certain constraints, the Two Pointers approach becomes quite useful.
 * <p>
 * fast slow pointer
 * uses two pointers which move through the array (or sequence/LinkedList) at different speeds.
 * This approach is quite useful when dealing with cyclic LinkedLists or arrays.
 */
public class Pattern {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Condition condition = lock.newCondition();
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread1 get lock");
                condition.await(2, TimeUnit.SECONDS);
                System.out.println("Thread1 get lock again");
            } catch (InterruptedException e) {
                System.out.println("Thead1 get e " + e.getCause());
            } finally {
                lock.unlock();
                System.out.println("Thread1 finish.");
            }
        });
        thread1.start();

        Thread.sleep(1000);

        executorService.submit(() -> {
            lock.lock();
            try {
                System.out.println("Thread2 get lock");
                System.out.println("interrupted Thread1");
                thread1.interrupt();
                Thread.sleep(10* 1000);
                condition.signal();

            } catch (InterruptedException e) {
                System.out.println("Thead2 get e " + e.getCause());
            } finally {
                System.out.println("Thread2 finish.");
                lock.unlock();
            }
        });

    }

}

