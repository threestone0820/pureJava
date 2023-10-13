package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0071_SimplifyPath {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        List<String> list = new ArrayList<>();
        for (String s : paths) {
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(s);
            }
        }
        if (list.isEmpty()) {
            return "/";
        }
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append("/");
            builder.append(s);
        }
        return builder.toString();
    }
}
