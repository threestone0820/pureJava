package three.stone.algorithm.sliding_window;


import java.util.ArrayList;

/**
 * Given an array of integers Arr of size N and a number K.
 * Return the maximum sum of a subarray of size K.
 * Example 1:
 *
 * Input:
 * N = 4, K = 2
 * Arr = [100, 200, 300, 400]
 * Output:
 * 700
 * Explanation:
 * Arr3  + Arr4 =700,
 * which is maximum.
 */
public class Maximum_Sum_Subarray_Of_Size_K {
    static int maximumSumSubarray(int K, ArrayList<Integer> Arr, int N){
        if (K > N || N == 0) {
            return 0;
        }

        int start = 0, end = 0, result, curMax = 0;
        while (end < K) {
            curMax += Arr.get(end++);
        }
        result = curMax;

        while (end < N) {
            curMax += Arr.get(end++);
            curMax -= Arr.get(start++);
            result = Math.max(curMax, result);
        }
        return result;
    }
}
