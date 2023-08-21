package three.stone.algorithm.leetcode;

/**
 * There are n flights that are labeled from 1 to n.
 *
 * You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi]
 * represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.
 *
 * Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
 *
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 * Explanation:
 * Flight labels:        1   2   3   4   5
 * Booking 1 reserved:  10  10
 * Booking 2 reserved:      20  20
 * Booking 3 reserved:      25  25  25  25
 * Total seats:         10  55  45  25  25
 * Hence, answer = [10,55,45,25,25]
 */
public class _1109_CorporateFlightBookings {
    /**
     * diff数组: 主要适用场景是频繁对原始数组的某个区间的元素进行增减
     * 例如：如果你想对区间 nums[i..j] 的元素全部加 3，那么只需要让 diff[i] += 3，然后再让 diff[j+1] -= 3 即可
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1];
            int reserve = booking[2];
            result[start - 1] += reserve;
            if (end < n) {
                result[end] -= reserve;
            }
        }

        for (int i = 1; i < n; i++) {
            result[i] += result[i - 1];
        }
        return result;
    }
}
