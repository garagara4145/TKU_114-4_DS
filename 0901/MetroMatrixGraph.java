import java.util.*;

public class MetroMatrixGraph {

    private final List<String> stations;
    private final Map<String, Integer> indexMap;
    private final boolean[][] matrix;
    private int edgeCount;

    public MetroMatrixGraph(List<String> stations) {
        if (stations == null || stations.isEmpty()) {
            throw new IllegalArgumentException("stations");
        }

        this.stations = new ArrayList<>(stations);
        this.indexMap = new LinkedHashMap<>();

        for (int i = 0; i < this.stations.size(); i++) {
            String station = this.stations.get(i);

            if (station == null || station.isBlank()) {
                throw new IllegalArgumentException("station");
            }

            if (indexMap.containsKey(station)) {
                throw new IllegalArgumentException("duplicate station");
            }

            indexMap.put(station, i);
        }

        matrix = new boolean[this.stations.size()]
                              [this.stations.size()];

        edgeCount = 0;
    }

    private int indexOf(String station) {
        Integer index = indexMap.get(station);

        if (index == null) {
            throw new IllegalArgumentException(
                "Unknown station: " + station
            );
        }

        return index;
    }

    public boolean addEdge(String from, String to) {

        int a = indexOf(from);
        int b = indexOf(to);

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

    public boolean removeEdge(String from, String to) {

        int a = indexOf(from);
        int b = indexOf(to);

        if (!matrix[a][b]) {
            return false;
        }

        matrix[a][b] = false;
        matrix[b][a] = false;

        edgeCount--;

        return true;
    }

    public boolean hasEdge(String from, String to) {
        int a = indexOf(from);
        int b = indexOf(to);

        return matrix[a][b];
    }

    public int degree(String station) {

        int index = indexOf(station);
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String station) {

        int index = indexOf(station);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[index][i]) {
                result.add(stations.get(i));
            }
        }

        return result;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void printMatrix() {

        System.out.println("--- Metro Matrix ---");

        System.out.print("    ");

        for (String station : stations) {
            System.out.printf("%4s", station);
        }

        System.out.println();

        for (int i = 0; i < stations.size(); i++) {

            System.out.printf("%4s", stations.get(i));

            for (int j = 0; j < stations.size(); j++) {

                System.out.printf(
                    "%4d",
                    matrix[i][j] ? 1 : 0
                );
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        MetroMatrixGraph metro =
            new MetroMatrixGraph(
                Arrays.asList("A", "B", "C", "D", "E")
            );

        System.out.println(
            "A-B: " + metro.addEdge("A", "B")
        );

        System.out.println(
            "A-C: " + metro.addEdge("A", "C")
        );

        System.out.println(
            "B-D: " + metro.addEdge("B", "D")
        );

        System.out.println(
            "C-D: " + metro.addEdge("C", "D")
        );

        System.out.println(
            "A-B again: " + metro.addEdge("A", "B")
        );

        System.out.println();

        System.out.println(
            "A neighbors: " + metro.neighbors("A")
        );

        System.out.println(
            "A degree: " + metro.degree("A")
        );

        System.out.println(
            "D neighbors: " + metro.neighbors("D")
        );

        System.out.println(
            "D degree: " + metro.degree("D")
        );

        System.out.println();

        System.out.println(
            "Edge count: " + metro.edgeCount()
        );

        System.out.println(
            "A-C exists: " + metro.hasEdge("A", "C")
        );

        System.out.println(
            "Remove A-C: " + metro.removeEdge("A", "C")
        );

        System.out.println(
            "A-C exists: " + metro.hasEdge("A", "C")
        );

        System.out.println(
            "Edge count: " + metro.edgeCount()
        );

        System.out.println();

        metro.printMatrix();
    }
}