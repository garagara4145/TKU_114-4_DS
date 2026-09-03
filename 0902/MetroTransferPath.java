import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {

    static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> path =
                new ArrayList<>();

        if (graph == null
                || start == null
                || target == null
                || !graph.containsKey(start)
                || !graph.containsKey(target)) {
            return path;
        }

        Queue<String> queue =
                new ArrayDeque<>();

        Set<String> visited =
                new HashSet<>();

        Map<String, String> previous =
                new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (String next :
                    graph.getOrDefault(
                            current,
                            List.of())) {

                if (graph.containsKey(next)
                        && visited.add(next)) {

                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(target)) {
            return path;
        }

        String current = target;

        while (current != null) {
            path.add(current);

            if (current.equals(start)) {
                break;
            }

            current = previous.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    static int edgeCount(
            List<String> path) {

        if (path == null || path.size() <= 1) {
            return 0;
        }

        return path.size() - 1;
    }

    static void printPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> path =
                shortestPath(
                        graph,
                        start,
                        target);

        System.out.println(
                start + " 到 " + target
                        + " 的最少站數路徑 = "
                        + path);

        System.out.println(
                "邊數 = "
                        + edgeCount(path));
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D"));
        graph.put("C", List.of("A", "D", "E"));
        graph.put("D", List.of("B", "C", "F"));
        graph.put("E", List.of("C", "F"));
        graph.put("F", List.of("D", "E"));
        graph.put("G", List.of());

        printPath(graph, "A", "F");

        printPath(graph, "A", "D");

        printPath(graph, "B", "E");

        printPath(graph, "A", "A");

        printPath(graph, "A", "G");

        printPath(graph, "A", "X");

        printPath(graph, "X", "A");

        System.out.println(
                "空路徑邊數 = "
                        + edgeCount(
                                new ArrayList<>()));
    }
}