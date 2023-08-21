package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 *
 * Given a string s that represents a DNA sequence,
 * return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class _0187_RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Set<Integer> hashOfSubString = new HashSet<>();
        Set<Integer> addedHash = new HashSet<>();
        char[] chars = s.toCharArray();
        if (s.length() < 11) {
            return result;
        }

        /**
         * 把这个字符串看成四进制
         * A: 00
         * C: 01
         * G: 10
         * T: 11
         *
         * 比如：
         * A A C C T C C G G T
         * 可以表示为
         * 00 00 01 01 11 01 01 10 10 11
         *
         * 这样可以计算长度为10的子串的hash值，而且这个hash值比较小，长度为20位
         * 如果字符串中包含很多种字符，那用这个方法来存储hash值似乎不太可行
         * 而如果字符串中的字符种类很少，可以考虑这个思路
         */

        int[] map = new int[126];
        map['A' - 'A'] = 0b00;
        map['C' - 'A'] = 0b01;
        map['G' - 'A'] = 0b10;
        map['T' - 'A'] = 0b11;
        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash <<= 2;
            hash |= map[chars[i] - 'A'];
        }
        hashOfSubString.add(hash);

        for (int i = 10; i < chars.length; i++) {
            // 在滑动窗口的过程中，不用重复计算，
            hash <<= 2;
            // 加上右边两位
            hash |= map[chars[i] - 'A'];
            // 移除最左边的第18 19 位
            hash &= 0x0FFFFF;
            // 技巧：add return true if this set did not already contain the specified
            if (!hashOfSubString.add(hash) && addedHash.add(hash)) {
                result.add(s.substring(i - 9, i + 1));
                addedHash.add(hash);
            }
        }
        return result;
    }
}
