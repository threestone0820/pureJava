package three.stone.algorithm.cyclic_sort;

/**
 * Given an unsorted array Arr of size N of positive integers. One number 'A' from set {1, 2, …N}
 * is missing and one number 'B' occurs twice in array. Find these two numbers.
 *
 * Example 1:
 *
 * Input:
 * N = 2
 * Arr[] = {2, 2}
 * Output: 2 1
 * Explanation: Repeating number is 2 and
 * smallest positive missing number is 1.
 * Example 2:
 *
 * Input:
 * N = 3
 * Arr[] = {1, 3, 3}
 * Output: 3 2
 */
public class Find_Missing_And_Repeating {
    int[] findTwoElement(int arr[], int n) {
        int i = 0;
        while (i < n) {
            int index = arr[i] - 1;
            if (arr[index] != arr[i]) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            } else {
                i++;
            }
        }

        i = 0;
        while (i < n) {
            // 注意需要index - 1 和 +1 的地方
            if (arr[i] - 1 != i) {
                return new int[]{arr[i], i + 1};
            }
            i++;
        }
        return null;
    }
}
