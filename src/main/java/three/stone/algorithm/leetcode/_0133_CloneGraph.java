package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0133_CloneGraph {
    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (null == node) {
            return null;
        }
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        List<Node> list = new ArrayList<>();
        for (Node neighbor : node.neighbors) {
            list.add(map.containsKey(neighbor) ? map.get(neighbor) : dfs(neighbor, map));
        }
        newNode.neighbors = list;
        return newNode;
    }
}