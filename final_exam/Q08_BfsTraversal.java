import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {

    public static List<String> bfs(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String next : neighbors) {
                if (next != null
                        && graph.containsKey(next)
                        && !visited.contains(next)) {

                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    public static Map<String, Integer> distanceFrom(
            Map<String, List<String>> graph, String start) {

        Map<String, Integer> distance = new HashMap<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return distance;
        }

        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String next : neighbors) {
                if (next != null
                        && graph.containsKey(next)
                        && !distance.containsKey(next)) {

                    distance.put(
                            next,
                            distance.get(current) + 1
                    );

                    queue.offer(next);
                }
            }
        }

        return distance;
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

        System.out.println("BFS A = "
                + bfs(graph, "A"));

        System.out.println("A距離 "
                + distanceFrom(graph, "A"));

        System.out.println("BFS D = "
                + bfs(graph, "D"));

        System.out.println("D距離 "
                + distanceFrom(graph, "D"));

        System.out.println("不存在的 G "
                + bfs(graph, "G"));

        System.out.println("起點 "
                + distanceFrom(graph, null));
    }
}