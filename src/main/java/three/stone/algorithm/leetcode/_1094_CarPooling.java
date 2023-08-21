package three.stone.algorithm.leetcode;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off
 * are fromi and toi respectively. The locations are given as the number of kilometers
 * due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 * Constraints:
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class _1094_CarPooling {
    /**
     * 用diff数组来解决
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 一共有1001个车站
        int[] locations = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0];
            locations[trip[1]] += num;
            locations[trip[2]] -= num;
        }
        // 从1开始累加
        for (int i = 1; i < 1000; i++) {
            locations[i] = locations[i] + locations[i - 1];
        }
        // 从0开始判断
        for (int i = 0; i < 1000; i++) {
            if (locations[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
