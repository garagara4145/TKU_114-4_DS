import java.util.ArrayList;
import java.util.List;

public class Q06_AdjacencyMatrixGraph {

    private ArrayList<String> vertices;
    private boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertices = new ArrayList<>();

        if (vertices != null) {
            for (String vertex : vertices) {
                if (vertex != null && !this.vertices.contains(vertex)) {
                    this.vertices.add(vertex);
                }
            }
        }

        matrix = new boolean[this.vertices.size()][this.vertices.size()];
    }

    private int indexOf(String vertex) {
        return vertices.indexOf(vertex);
    }

    public boolean addEdge(String first, String second) {
        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        if (firstIndex == secondIndex) {
            return false;
        }

        if (matrix[firstIndex][secondIndex]) {
            return false;
        }

        matrix[firstIndex][secondIndex] = true;
        matrix[secondIndex][firstIndex] = true;

        return true;
    }

    public boolean removeEdge(String first, String second) {
        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        if (!matrix[firstIndex][secondIndex]) {
            return false;
        }

        matrix[firstIndex][secondIndex] = false;
        matrix[secondIndex][firstIndex] = false;

        return true;
    }

    public boolean hasEdge(String first, String second) {
        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        return matrix[firstIndex][secondIndex];
    }

    public int degree(String vertex) {
        int index = indexOf(vertex);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < matrix[index].length; i++) {
            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String vertex) {
        int index = indexOf(vertex);
        List<String> result = new ArrayList<>();

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[index][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<String> vertices = new ArrayList<>();

        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");

        Q06_AdjacencyMatrixGraph graph =
                new Q06_AdjacencyMatrixGraph(vertices);

        System.out.println("A-B "
                + graph.addEdge("A", "B"));

        System.out.println("A-C "
                + graph.addEdge("A", "C"));

        System.out.println("B-D "
                + graph.addEdge("B", "D"));

        System.out.println("repeat A-B "
                + graph.addEdge("A", "B"));

        System.out.println("A-A "
                + graph.addEdge("A", "A"));

        System.out.println("A-E "
                + graph.addEdge("A", "E"));

        System.out.println("A是否連接B "
                + graph.hasEdge("A", "B"));

        System.out.println("B是否連接A "
                + graph.hasEdge("B", "A"));

        System.out.println("A等級"
                + graph.degree("A"));

        System.out.println("B等級"
                + graph.degree("B"));

        System.out.println("A的鄰居"
                + graph.neighbors("A"));

        System.out.println("B的鄰居"
                + graph.neighbors("B"));

        System.out.println("移除 A-B"
                + graph.removeEdge("A", "B"));

        System.out.println("A是否連接B"
                + graph.hasEdge("A", "B"));

        System.out.println("A的鄰居"
                + graph.neighbors("A"));

        System.out.println("查詢E"
                + graph.neighbors("E"));

        System.out.println("E等級"
                + graph.degree("E"));
    }
}