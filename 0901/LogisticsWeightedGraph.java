import java.util.*;

public class LogisticsWeightedGraph {

    private static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return to + "(" + weight + ")";
        }
    }

    private final Map<String, Map<String, Integer>> graph =
        new LinkedHashMap<>();

    public void addWarehouse(String warehouse) {

        if (warehouse == null || warehouse.isBlank()) {
            throw new IllegalArgumentException("warehouse");
        }

        graph.putIfAbsent(
            warehouse.trim(),
            new LinkedHashMap<>()
        );
    }

    public boolean addRoute(
        String from,
        String to,
        int weight
    ) {

        checkWarehouse(from);
        checkWarehouse(to);

        if (weight < 0) {
            throw new IllegalArgumentException(
                "weight cannot be negative"
            );
        }

        Map<String, Integer> routes =
            graph.get(from);

        boolean isNew = !routes.containsKey(to);

        routes.put(to, weight);

        return isNew;
    }

    public boolean removeRoute(
        String from,
        String to
    ) {

        checkWarehouse(from);
        checkWarehouse(to);

        Map<String, Integer> routes =
            graph.get(from);

        return routes.remove(to) != null;
    }

    public Integer getWeight(
        String from,
        String to
    ) {

        checkWarehouse(from);
        checkWarehouse(to);

        return graph.get(from).get(to);
    }

    public boolean hasRoute(
        String from,
        String to
    ) {

        checkWarehouse(from);
        checkWarehouse(to);

        return graph.get(from).containsKey(to);
    }

    public Set<String> outgoingWarehouses(
        String warehouse
    ) {

        checkWarehouse(warehouse);

        return new LinkedHashSet<>(
            graph.get(warehouse).keySet()
        );
    }

    public int outDegree(String warehouse) {

        checkWarehouse(warehouse);

        return graph.get(warehouse).size();
    }

    public int edgeCount() {

        int count = 0;

        for (Map<String, Integer> routes :
             graph.values()) {

            count += routes.size();
        }

        return count;
    }

    public void printGraph() {

        System.out.println(
            "--- Logistics Weighted Graph ---"
        );

        for (String warehouse : graph.keySet()) {

            System.out.print(
                warehouse + " -> "
            );

            Map<String, Integer> routes =
                graph.get(warehouse);

            if (routes.isEmpty()) {
                System.out.println("[]");
                continue;
            }

            List<String> result =
                new ArrayList<>();

            for (Map.Entry<String, Integer> entry :
                 routes.entrySet()) {

                result.add(
                    entry.getKey()
                    + "("
                    + entry.getValue()
                    + ")"
                );
            }

            System.out.println(result);
        }
    }

    private void checkWarehouse(
        String warehouse
    ) {

        if (warehouse == null ||
            !graph.containsKey(warehouse)) {

            throw new IllegalArgumentException(
                "Unknown warehouse: "
                + warehouse
            );
        }
    }

    public static void main(String[] args) {

        LogisticsWeightedGraph graph =
            new LogisticsWeightedGraph();

        graph.addWarehouse("A");
        graph.addWarehouse("B");
        graph.addWarehouse("C");
        graph.addWarehouse("D");

        System.out.println(
            "A -> B: "
            + graph.addRoute("A", "B", 10)
        );

        System.out.println(
            "A -> C: "
            + graph.addRoute("A", "C", 25)
        );

        System.out.println(
            "B -> C: "
            + graph.addRoute("B", "C", 15)
        );

        System.out.println(
            "C -> D: "
            + graph.addRoute("C", "D", 20)
        );

        System.out.println();

        System.out.println(
            "A -> B weight: "
            + graph.getWeight("A", "B")
        );

        System.out.println(
            "A -> C weight: "
            + graph.getWeight("A", "C")
        );

        System.out.println();

        System.out.println(
            "Update A -> B: "
            + graph.addRoute("A", "B", 12)
        );

        System.out.println(
            "A -> B new weight: "
            + graph.getWeight("A", "B")
        );

        System.out.println();

        graph.printGraph();

        System.out.println();

        System.out.println(
            "A outgoing: "
            + graph.outgoingWarehouses("A")
        );

        System.out.println(
            "A out-degree: "
            + graph.outDegree("A")
        );

        System.out.println(
            "Edge count: "
            + graph.edgeCount()
        );

        System.out.println();

        System.out.println(
            "Remove A -> C: "
            + graph.removeRoute("A", "C")
        );

        System.out.println(
            "A -> C exists: "
            + graph.hasRoute("A", "C")
        );

        System.out.println(
            "Edge count: "
            + graph.edgeCount()
        );

        System.out.println();

        try {
            graph.addRoute("A", "D", -5);
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Negative weight rejected: "
                + e.getMessage()
            );
        }

        try {
            graph.addRoute("X", "A", 10);
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Unknown warehouse rejected: "
                + e.getMessage()
            );
        }
    }
}