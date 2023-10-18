package three.stone.algorithm.leetcode;

public class _0201_BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        int moved = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            moved++;
        }
        return left << moved;
    }
}
