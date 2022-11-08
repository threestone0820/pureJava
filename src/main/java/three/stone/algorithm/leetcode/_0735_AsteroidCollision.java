package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed. tfttrfrf
 *
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * asteroids[i] != 0
 */
public class _0735_AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>();
        for (int i : asteroids) {
            if (i > 0)
                s.add(i);
            else {
                while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                    s.pollLast();
                if (!s.isEmpty() && s.getLast() == -i)
                    s.pollLast();
                else if (s.isEmpty() || s.getLast() < 0)
                    s.add(i);
            }
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}
