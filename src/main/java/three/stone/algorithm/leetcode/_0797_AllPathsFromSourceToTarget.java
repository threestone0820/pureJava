package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0797_AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        boolean[] onPath = new boolean[graph.length];
        traverse(result, graph, 0, visited, onPath);
        return result;
    }

    private void traverse(List<List<Integer>> result, int[][] graph, int i, boolean[] visited, boolean[] onPath) {
        if (visited[i]) {
            return;
        }
        if (i == graph.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < onPath.length; j++) {
                if (onPath[j]) {
                    path.add(j);
                }
            }
            result.add(path);
            return;
        }
        visited[i] = true;
        onPath[i] = true;
        for (int j : graph[i]) {
            traverse(result, graph, j, visited, onPath);
        }
        onPath[i] = false;
    }
}
