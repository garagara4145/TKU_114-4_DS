import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

public class DirectedReachability {

    static boolean reachable(
            Map<String, List<String>> graph,
            String from,
            String to) {

        if (graph == null
                || from == null
                || to == null
                || !graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        Queue<String> queue =
                new ArrayDeque<>();

        Set<String> visited =
                new HashSet<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(to)) {
                return true;
            }

            for (String next :
                    graph.getOrDefault(
                            current,
                            List.of())) {

                if (graph.containsKey(next)
                        && visited.add(next)) {

                    queue.offer(next);
                }
            }
        }

        return false;
    }

    static List<Boolean> checkQueries(
            Map<String, List<String>> graph,
            List<String[]> queries) {

        List<Boolean> result =
                new ArrayList<>();

        if (queries == null) {
            return result;
        }

        for (String[] query : queries) {

            if (query == null
                    || query.length < 2) {

                result.add(false);
                continue;
            }

            result.add(
                    reachable(
                            graph,
                            query[0],
                            query[1]));
        }

        return result;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of("E"));
        graph.put("E", List.of());
        graph.put("F", List.of("G"));
        graph.put("G", List.of());

        System.out.println(
                "A 到 E = "
                        + reachable(graph, "A", "E"));

        System.out.println(
                "A 到 D = "
                        + reachable(graph, "A", "D"));

        System.out.println(
                "E 到 A = "
                        + reachable(graph, "E", "A"));

        System.out.println(
                "A 到 A = "
                        + reachable(graph, "A", "A"));

        System.out.println(
                "F 到 G = "
                        + reachable(graph, "F", "G"));

        System.out.println(
                "G 到 F = "
                        + reachable(graph, "G", "F"));

        System.out.println(
                "A 到 X = "
                        + reachable(graph, "A", "X"));

        System.out.println(
                "X 到 A = "
                        + reachable(graph, "X", "A"));

        List<String[]> queries =
                new ArrayList<>();

        queries.add(new String[]{"A", "E"});
        queries.add(new String[]{"A", "F"});
        queries.add(new String[]{"B", "E"});
        queries.add(new String[]{"F", "G"});
        queries.add(new String[]{"G", "F"});

        System.out.println(
                "多組查詢 = "
                        + checkQueries(
                                graph,
                                queries));

        System.out.println(
                "空查詢 = "
                        + checkQueries(
                                graph,
                                new ArrayList<>()));

        System.out.println(
                "null 查詢 = "
                        + checkQueries(
                                graph,
                                null));

        System.out.println(
                "null Graph = "
                        + reachable(
                                null,
                                "A",
                                "B"));
    }
}