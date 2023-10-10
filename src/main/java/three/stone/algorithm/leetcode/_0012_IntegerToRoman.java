package three.stone.algorithm.leetcode;

public class _0012_IntegerToRoman {
    public String intToRoman(int num) {
        String[] thousand = new String[]{"", "M", "MM", "MMM"};
        String[] hundred = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] ten = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] one = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String result = "";
        result += thousand[num / 1000];
        num %= 1000;
        result += hundred[num / 100];
        num %= 100;
        result += ten[num / 10];
        num %= 10;
        result += one[num];
        return result;
    }
}
