package three.stone.algorithm.merge_intervals;

import three.stone.algorithm.merge_intervals.Meeting_Rooms.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given a list schedule of employees,
 * which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals,
 * and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time
 * for all employees, also in sorted order.
 *
 * The Intervals is an 1d-array. Each two numbers shows an interval.
 * For example, [1,2,8,10] represents that the employee works in [1,2] and [8,10].
 *
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * 1.schedule and schedule[i] are lists with lengths in range [1, 100].
 * 2.0 <= schedule[i].start < schedule[i].end <= 10^8.
 *
 * Example
 * Example 1:
 * Input：schedule = [[1,2,5,6],[1,3],[4,10]]
 * Output：[(3,4)]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 *
 * Input：schedule = [[1,3,6,7],[2,4],[2,5,9,12]]
 * Output：[(5,6),(7,9)]
 * Explanation：
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [5, 6], [7, 9],[12,inf].
 * We discard any intervals that contain inf as they aren't finite.
 */
public class Employee_Free_Time {
    // accepted
    public List<Interval> employeeFreeTime(int[][] schedule) {
        int[] schedules = schedule[0];
        List<Interval> resultInterval = freeInterval(schedules);
        for (int i = 1; i < schedule.length; i++) {
            resultInterval = overlapInterval(resultInterval, freeInterval(schedule[i]));
        }

        resultInterval.remove(0);
        resultInterval.remove(resultInterval.size() - 1);
        return resultInterval;
    }

    private List<Interval> overlapInterval(List<Interval> first, List<Interval> second) {
        List<Interval> overlap = new ArrayList<>();
        int i = 0, j = 0;
        while (i < first.size() && j < second.size()) {
            if (first.get(i).start >= second.get(j).end) {
                j++;
            } else if (first.get(i).end <= second.get(j).start) {
                i++;
            } else {
                overlap.add(new Interval(
                        Math.max(first.get(i).start, second.get(j).start),
                        Math.min(first.get(i).end, second.get(j).end)
                ));
                if (first.get(i).end > second.get(j).end) {
                    j++;
                } else {
                    i++;
                }
            }
        }
        return overlap;
    }

    private List<Interval> freeInterval(int[] schedules) {
        List<Interval> result = new ArrayList<>();
        result.add(new Interval(Integer.MIN_VALUE, schedules[0]));
        int i = 2;
        while (i < schedules.length) {
            result.add(new Interval(schedules[i - 1], schedules[i]));
            i += 2;
        }
        result.add(new Interval(schedules[schedules.length - 1], Integer.MAX_VALUE));
        return result;
    }
}
