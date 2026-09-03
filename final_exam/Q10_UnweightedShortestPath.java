import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> result = new ArrayList<>();

        if (graph == null
                || start == null
                || target == null
                || !graph.containsKey(start)
                || !graph.containsKey(target)) {

            return result;
        }

        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String next : neighbors) {
                if (next != null
                        && graph.containsKey(next)
                        && !visited.contains(next)) {

                    visited.add(next);
                    predecessor.put(next, current);
                    queue.offer(next);

                    if (next.equals(target)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if (!visited.contains(target)) {
            return result;
        }

        String current = target;

        while (current != null) {
            result.add(0, current);

            if (current.equals(start)) {
                break;
            }

            current = predecessor.get(current);
        }

        if (!result.get(0).equals(start)) {
            return new ArrayList<>();
        }

        return result;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("A", new ArrayList<>());
        graph.put("B", new ArrayList<>());
        graph.put("C", new ArrayList<>());
        graph.put("D", new ArrayList<>());
        graph.put("E", new ArrayList<>());
        graph.put("F", new ArrayList<>());
        graph.put("G", new ArrayList<>());

        graph.get("A").add("B");
        graph.get("A").add("C");

        graph.get("B").add("D");
        graph.get("B").add("E");

        graph.get("C").add("F");

        graph.get("D").add("G");
        graph.get("E").add("G");

        graph.get("F").add("G");

        System.out.println("A to G 的最短路徑 = "
                + shortestPath(graph, "A", "G"));

        System.out.println("A to D 的最短路徑 = "
                + shortestPath(graph, "A", "D"));

        System.out.println("B to G 的最短路徑 = "
                + shortestPath(graph, "B", "G"));

        System.out.println("A to A 的最短路徑 = "
                + shortestPath(graph, "A", "A"));

        System.out.println("A to Z 的最短路徑 = "
                + shortestPath(graph, "A", "Z"));

        System.out.println("Z to A 的最短路徑 = "
                + shortestPath(graph, "Z", "A"));
    }
}