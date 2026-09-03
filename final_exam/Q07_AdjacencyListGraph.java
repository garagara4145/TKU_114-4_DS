import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {

    private Map<String, Set<String>> graph;
    private int edges;

    public Q07_AdjacencyListGraph() {
        graph = new HashMap<>();
        edges = 0;
    }

    public boolean addVertex(String vertex) {
        if (vertex == null || graph.containsKey(vertex)) {
            return false;
        }

        graph.put(vertex, new java.util.LinkedHashSet<>());

        return true;
    }

    public boolean addEdge(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        Set<String> neighbors = graph.get(from);

        if (neighbors.contains(to)) {
            return false;
        }

        neighbors.add(to);
        edges++;

        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        Set<String> neighbors = graph.get(from);

        if (!neighbors.contains(to)) {
            return false;
        }

        neighbors.remove(to);
        edges--;

        return true;
    }

    public List<String> outgoing(String vertex) {
        if (!graph.containsKey(vertex)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(graph.get(vertex));
    }

    public int inDegree(String vertex) {
        if (!graph.containsKey(vertex)) {
            return 0;
        }

        int count = 0;

        for (Set<String> neighbors : graph.values()) {
            if (neighbors.contains(vertex)) {
                count++;
            }
        }

        return count;
    }

    public int edgeCount() {
        return edges;
    }

    public static void main(String[] args) {

        Q07_AdjacencyListGraph graph =
                new Q07_AdjacencyListGraph();

        System.out.println("新增A "
                + graph.addVertex("A"));

        System.out.println("新增B "
                + graph.addVertex("B"));

        System.out.println("新增C  "
                + graph.addVertex("C"));

        System.out.println("新增D  "
                + graph.addVertex("D"));

        System.out.println("新增 A to B "
                + graph.addEdge("A", "B"));

        System.out.println("新增 A to C "
                + graph.addEdge("A", "C"));

        System.out.println("新增 B to C "
                + graph.addEdge("B", "C"));

        System.out.println("新增 C to A "
                + graph.addEdge("C", "A"));

        System.out.println("重複新增 A to B "
                + graph.addEdge("A", "B"));

        System.out.println("新增 A to A "
                + graph.addEdge("A", "A"));

        System.out.println("新增 A to E "
                + graph.addEdge("A", "E"));

        System.out.println("A延伸"
                + graph.outgoing("A"));

        System.out.println("B延伸"
                + graph.outgoing("B"));

        System.out.println("C延伸"
                + graph.outgoing("C"));

        System.out.println("C 的入度 = "
                + graph.inDegree("C"));

        System.out.println("A 的入度 = "
                + graph.inDegree("A"));

        System.out.println("邊數量 = "
                + graph.edgeCount());

        System.out.println("移除 A to B "
                + graph.removeEdge("A", "B"));

        System.out.println("A延伸 "
                + graph.outgoing("A"));

        System.out.println("移除後邊數量 = "
                + graph.edgeCount());

        System.out.println("查詢不存在的 E = "
                + graph.outgoing("E"));

        System.out.println("E 的入度 = "
                + graph.inDegree("E"));
    }
}