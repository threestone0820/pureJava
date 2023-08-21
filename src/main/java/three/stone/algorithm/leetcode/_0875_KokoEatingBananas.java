package three.stone.algorithm.leetcode;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas
 * and eats k bananas from that pile. If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 */
public class _0875_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = piles[0];
        for (int pile : piles) {
            right = Math.max(pile, right);
        }

        while (left < right) {
            int needHour = 0, mid = left + (right - left) / 2;
            for (int pile : piles) {
                needHour += (pile + mid - 1) / mid;
            }
            if (needHour > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
