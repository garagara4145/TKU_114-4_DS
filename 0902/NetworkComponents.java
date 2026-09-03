import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {

    static List<List<String>> components(
            Map<String, List<String>> graph) {

        List<List<String>> result =
                new ArrayList<>();

        if (graph == null) {
            return result;
        }

        Set<String> visited =
                new HashSet<>();

        for (String start : graph.keySet()) {

            if (visited.contains(start)) {
                continue;
            }

            List<String> component =
                    new ArrayList<>();

            Queue<String> queue =
                    new ArrayDeque<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {

                String current =
                        queue.poll();

                component.add(current);

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

            result.add(component);
        }

        return result;
    }

    static int componentCount(
            Map<String, List<String>> graph) {

        return components(graph).size();
    }

    static List<String> largestComponent(
            Map<String, List<String>> graph) {

        List<List<String>> all =
                components(graph);

        List<String> largest =
                new ArrayList<>();

        for (List<String> component : all) {

            if (component.size()
                    > largest.size()) {

                largest = component;
            }
        }

        return new ArrayList<>(largest);
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new LinkedHashMap<>();

        graph.put("A", List.of("B"));
        graph.put("B", List.of("A", "C"));
        graph.put("C", List.of("B"));

        graph.put("D", List.of("E"));
        graph.put("E", List.of("D"));

        graph.put("F", List.of());

        graph.put("G", List.of("H"));
        graph.put("H", List.of("G"));

        List<List<String>> result =
                components(graph);

        System.out.println(
                "所有分量 = " + result);

        System.out.println(
                "分量個數 = "
                        + componentCount(graph));

        System.out.println(
                "最大分量 = "
                        + largestComponent(graph));

        System.out.println();

        Map<String, List<String>> emptyGraph =
                new LinkedHashMap<>();

        System.out.println(
                "空 Graph = "
                        + components(emptyGraph));

        System.out.println(
                "空 Graph 分量個數 = "
                        + componentCount(emptyGraph));

        System.out.println(
                "空 Graph 最大分量 = "
                        + largestComponent(emptyGraph));

        System.out.println();

        System.out.println(
                "null Graph = "
                        + components(null));
    }
}