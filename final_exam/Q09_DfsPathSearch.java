import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q09_DfsPathSearch {

    public static List<String> dfs(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        dfsRecursive(graph, start, visited, result);

        return result;
    }

    private static void dfsRecursive(
            Map<String, List<String>> graph,
            String current,
            Set<String> visited,
            List<String> result) {

        visited.add(current);
        result.add(current);

        List<String> neighbors = graph.get(current);

        if (neighbors == null) {
            return;
        }

        for (String next : neighbors) {
            if (next != null
                    && graph.containsKey(next)
                    && !visited.contains(next)) {

                dfsRecursive(graph, next, visited, result);
            }
        }
    }

    public static boolean reachable(
            Map<String, List<String>> graph,
            String start,
            String target) {

        if (graph == null
                || start == null
                || target == null
                || !graph.containsKey(start)
                || !graph.containsKey(target)) {

            return false;
        }

        Set<String> visited = new HashSet<>();

        return reachableRecursive(
                graph,
                start,
                target,
                visited);
    }

    private static boolean reachableRecursive(
            Map<String, List<String>> graph,
            String current,
            String target,
            Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        List<String> neighbors = graph.get(current);

        if (neighbors == null) {
            return false;
        }

        for (String next : neighbors) {
            if (next != null
                    && graph.containsKey(next)
                    && !visited.contains(next)) {

                if (reachableRecursive(
                        graph,
                        next,
                        target,
                        visited)) {

                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("A", new ArrayList<>());
        graph.put("B", new ArrayList<>());
        graph.put("C", new ArrayList<>());
        graph.put("D", new ArrayList<>());
        graph.put("E", new ArrayList<>());
        graph.put("F", new ArrayList<>());

        graph.get("A").add("B");
        graph.get("A").add("C");

        graph.get("B").add("D");
        graph.get("B").add("E");

        graph.get("C").add("F");

        graph.get("D").add("A");

        System.out.println("DFS A "
                + dfs(graph, "A"));

        System.out.println("A to F "
                + reachable(graph, "A", "F"));

        System.out.println("A to E "
                + reachable(graph, "A", "E"));

        System.out.println("F to A "
                + reachable(graph, "F", "A"));

        System.out.println("A to A "
                + reachable(graph, "A", "A"));

        System.out.println("A to G "
                + reachable(graph, "A", "G"));

        System.out.println("G to A "
                + reachable(graph, "G", "A"));

        System.out.println("不存在的 G = "
                + dfs(graph, "G"));
    }
}