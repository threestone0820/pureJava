package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _0399_EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> chars = new HashSet<>();
        for (List<String> equation : equations) {
            chars.add(equation.get(0));
            chars.add(equation.get(1));
        }
        List<String> charList = new ArrayList<>();
        return null;
    }
}
