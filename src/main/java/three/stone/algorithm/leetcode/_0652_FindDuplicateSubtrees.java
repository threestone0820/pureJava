package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 */
public class _0652_FindDuplicateSubtrees {

    private String NULL = "#";
    private String SEPARATOR = ",";
    // 注意不要修饰成static 变量，leetcode 会复用静态变量
    private Map<String, List<TreeNode>> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        postOrder(root);
        map.forEach((k, v) -> {
            if (v.size() > 1) {
                result.add(v.get(0));
            }
        });
        return result;
    }

    // 注意：每个子树都可能是答案，所以我们要这个方法返回某个值
    // 仔细看这个方法的具体实现
    private String postOrder(TreeNode node) {
        if (node == null) {
            return SEPARATOR + NULL;
        }

        StringBuilder builder = new StringBuilder();
        String left = postOrder(node.left);
        String right = postOrder(node.right);
        builder.append(left);
        builder.append(right);
        builder.append(SEPARATOR);
        builder.append(node.val);
        String s = builder.toString();
        // 记住map的这个写法
        map.computeIfAbsent(s, k -> new ArrayList<>()).add(node);
        return s;
    }



}
