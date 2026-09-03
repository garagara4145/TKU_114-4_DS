import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BfsLayerReport {

    static Map<String, Integer> distances(
            Map<String, List<String>> graph,
            String start) {

        Map<String, Integer> distance =
                new LinkedHashMap<>();

        if (graph == null
                || start == null
                || !graph.containsKey(start)) {
            return distance;
        }

        Queue<String> queue =
                new ArrayDeque<>();

        Set<String> visited =
                new LinkedHashSet<>();

        queue.offer(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            for (String next :
                    graph.getOrDefault(
                            current,
                            List.of())) {

                if (graph.containsKey(next)
                        && visited.add(next)) {

                    distance.put(
                            next,
                            distance.get(current) + 1);

                    queue.offer(next);
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F"));
        graph.put("D", List.of("B"));
        graph.put("E", List.of("B", "F"));
        graph.put("F", List.of("C", "E"));
        graph.put("G", List.of());

        System.out.println(
                "A 的距離 "
                        + distances(graph, "A"));

        System.out.println(
                "D距離"
                        + distances(graph, "D"));

        System.out.println(
                "G距離 "
                        + distances(graph, "G"));

        System.out.println(
                "不存在的H "
                        + distances(graph, "H"));

        System.out.println(
                "起點 "
                        + distances(graph, null));

        System.out.println(
                "null Graph = "
                        + distances(null, "A"));
    }
}