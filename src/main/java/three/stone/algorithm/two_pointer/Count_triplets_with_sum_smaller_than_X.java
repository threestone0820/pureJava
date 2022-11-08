package three.stone.algorithm.two_pointer;

import java.util.Arrays;

/**
 * Given an array arr of unsorted numbers and a target sum, count all triplets in it
 * such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
 * Write a function to return the count of such triplets.
 *
 * Example 1:
 *
 * Input: [-1, 0, 2, 3], target=3
 * Output: 2
 * Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
 * Example 2:
 *
 * Input: [-1, 4, 2, 1, 3], target=5
 * Output: 4
 * Explanation: There are four triplets whose sum is less than the target:
 *    [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 */
public class Count_triplets_with_sum_smaller_than_X {
    long countTriplets(long arr[], int n, int sum) {
        Arrays.sort(arr);
        int result = 0;
        for (int i = 0; i < n - 2; ++i) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                long curSum = arr[i] + arr[j] + arr[k];
                if (curSum < sum) {
                    // j ~ k之间都满足要求
                    result += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
}
