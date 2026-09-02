import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private final List<String> vertices;
    private final boolean[][] matrix;
    private int edgeCount;

    public CampusMatrixGraph(List<String> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            throw new IllegalArgumentException("vertices");
        }

        this.vertices = List.copyOf(vertices);
        this.matrix = new boolean[vertices.size()][vertices.size()];
        this.edgeCount = 0;
    }

    private int indexOf(String vertex) {
        int index = vertices.indexOf(vertex);

        if (index < 0) {
            throw new IllegalArgumentException(
                "unknown vertex: " + vertex
            );
        }

        return index;
    }

    public boolean addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a == b) {
            return false;
        }

        if (matrix[a][b]) {
            return false;
        }

        matrix[a][b] = true;
        matrix[b][a] = true;

        edgeCount++;

        return true;
    }

    public boolean removeEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (!matrix[a][b]) {
            return false;
        }

        matrix[a][b] = false;
        matrix[b][a] = false;

        edgeCount--;

        return true;
    }

    public boolean hasEdge(String first, String second) {
        return matrix[indexOf(first)][indexOf(second)];
    }

    public int degree(String vertex) {
        int row = indexOf(vertex);
        int degree = 0;

        for (boolean connected : matrix[row]) {
            if (connected) {
                degree++;
            }
        }

        return degree;
    }

    public List<String> neighbors(String vertex) {
        int row = indexOf(vertex);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[row][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void printMatrix() {
        System.out.print("    ");

        for (String vertex : vertices) {
            System.out.print(vertex + " ");
        }

        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " : ");

            for (int j = 0; j < vertices.size(); j++) {
                System.out.print((matrix[i][j] ? 1 : 0) + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        CampusMatrixGraph graph =
            new CampusMatrixGraph(
                List.of("A", "B", "C", "D")
            );

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");

        graph.addEdge("A", "B");

        System.out.println(
            "A neighbors=" + graph.neighbors("A")
        );

        System.out.println(
            "A degree=" + graph.degree("A")
        );

        System.out.println(
            "C degree=" + graph.degree("C")
        );

        System.out.println(
            "edge count=" + graph.edgeCount()
        );

        System.out.println(
            "B-C=" + graph.hasEdge("B", "C")
        );

        System.out.println(
            "remove A-B=" + graph.removeEdge("A", "B")
        );

        System.out.println(
            "edge count=" + graph.edgeCount()
        );

        System.out.println("--- matrix ---");
        graph.printMatrix();
    }
}