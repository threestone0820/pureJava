package three.stone.algorithm.merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs
 * in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * Example 1:
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 104
 * 1 <= startTime[i] < endTime[i] <= 109
 * 1 <= profit[i] <= 104
 */
public class Maximum_Profit_in_Job_Scheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            tuples.add(new Tuple(startTime[i], endTime[i], profit[i]));
        }
        tuples.sort(Comparator.comparingInt(t -> t.end));

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);

        for (Tuple tuple : tuples) {
            int max = Math.max(
                    dp.floorEntry(tuple.start).getValue() + tuple.profit,
                    dp.lastEntry().getValue()
            );
            dp.put(tuple.end, max);
        }
        return dp.lastEntry().getValue();
    }

    static class Tuple{
        int start;
        int end;
        int profit;

        public Tuple(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
