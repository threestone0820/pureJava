package three.stone.algorithm.leetcode;

public class _0069_Sqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int low = 1, high = Integer.MAX_VALUE;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int y = x / mid;
            if (y == mid) {
                return mid;
            } else if (y < mid) {
                high = mid - 1;
            } else {
                if (x / (mid + 1) < (mid + 1)) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return low;
    }
}
