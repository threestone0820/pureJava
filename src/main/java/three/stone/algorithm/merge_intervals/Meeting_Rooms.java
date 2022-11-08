package three.stone.algorithm.merge_intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an 2D integer array A of size N x 2 denoting time intervals of different meetings.
 *
 * Where:
 *
 * A[i][0] = start time of the ith meeting.
 * A[i][1] = end time of the ith meeting.
 * Find the minimum number of conference rooms required so that all meetings can be done.
 *
 * Problem Constraints
 * 1 <= N <= 10
 *
 * 0 <= A[i][0] < A[i][1] <= 2 * 109
 *
 * Example Input
 * Input 1:
 *
 *  A = [      [0, 30]
 *             [5, 10]
 *             [15, 20]
 *      ]
 *
 * Input 2:
 *
 *  A =  [     [1, 18]
 *             [18, 23]
 *             [15, 29]
 *             [4, 15]
 *             [2, 11]
 *             [5, 13]
 *       ]
 *
 *
 * Example Output
 * Output 1:
 *
 *  2
 * Output 2:
 *
 *  4
 */
public class Meeting_Rooms {
    public int solve(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));
        int result = 1;
        LinkedList<Integer> finishTimes = new LinkedList<>();
        finishTimes.add(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
            result += meetingRoom(intervals.get(i).start, intervals.get(i).end, finishTimes);
        }

        return result;
    }

    private int meetingRoom(int startTime, int endTime, LinkedList<Integer> finishTimes) {
        for (int i = 0; i < finishTimes.size(); i++) {
            Integer finishTime = finishTimes.get(i);
            if (startTime > finishTime) {
                finishTimes.remove(i);
                finishTimes.add(endTime);
                return 0;
            }
        }

        finishTimes.add(endTime);
        return 1;
    }

    static class Interval{
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 使用了jdk的PriorityQueue，时间快些
    public int solveII(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));
        int result = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start > queue.peek()) {
                queue.poll();
            } else {
                result++;
            }
            queue.offer(intervals.get(i).end);
        }

        return result;
    }
}
