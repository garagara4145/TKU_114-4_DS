import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {

    static List<String> dfs(
            Map<String, List<String>> graph,
            String start) {

        List<String> result = new ArrayList<>();

        if (graph == null
                || start == null
                || !graph.containsKey(start)) {
            return result;
        }

        ArrayDeque<String> stack =
                new ArrayDeque<>();

        Set<String> visited =
                new LinkedHashSet<>();

        stack.push(start);

        System.out.println(
                "push " + start
                        + " | Stack = " + stack
                        + " | visited = " + visited);

        while (!stack.isEmpty()) {

            String current = stack.pop();

            System.out.println(
                    "pop " + current
                            + " | Stack = " + stack
                            + " | visited = " + visited);

            if (!visited.add(current)) {
                continue;
            }

            result.add(current);

            List<String> neighbors =
                    graph.getOrDefault(
                            current,
                            List.of());

            for (int i = neighbors.size() - 1;
                 i >= 0;
                 i--) {

                String next = neighbors.get(i);

                if (graph.containsKey(next)
                        && !visited.contains(next)) {

                    stack.push(next);

                    System.out.println(
                            "push " + next
                                    + " | Stack = " + stack
                                    + " | visited = " + visited);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D", "E"));
        graph.put("C", List.of("F"));
        graph.put("D", List.of("A"));
        graph.put("E", List.of("F"));
        graph.put("F", List.of());

        System.out.println("DFS 開始");
        List<String> result =
                dfs(graph, "A");

        System.out.println(
                "DFS 結果 = " + result);

        System.out.println();

        System.out.println("不存在的 G");
        System.out.println(
                "DFS 結果 = "
                        + dfs(graph, "G"));

        System.out.println();

        Map<String, List<String>> emptyGraph =
                new LinkedHashMap<>();

        System.out.println("空 Graph");
        System.out.println(
                "DFS 結果 = "
                        + dfs(emptyGraph, "A"));

        System.out.println();

        System.out.println("null Graph");
        System.out.println(
                "DFS 結果 = "
                        + dfs(null, "A"));
    }
}