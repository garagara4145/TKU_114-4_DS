import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    static void analyze(
            List<Integer> studentIds,
            int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException(
                    "bucketCount"
            );
        }

        List<List<Integer>> buckets =
                new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (studentIds != null) {

            for (Integer id : studentIds) {

                if (id == null) {
                    continue;
                }

                int index =
                        Math.floorMod(
                                Integer.hashCode(id),
                                bucketCount
                        );

                buckets.get(index).add(id);
            }
        }

        int totalEntries = 0;
        int collisionCount = 0;
        int maxChain = 0;

        System.out.println(
                "=== bucketCount="
                + bucketCount
                + " ==="
        );

        for (int i = 0; i < buckets.size(); i++) {

            List<Integer> chain =
                    buckets.get(i);

            int count = chain.size();

            totalEntries += count;

            if (count > 1) {
                collisionCount += count - 1;
            }

            maxChain =
                    Math.max(maxChain, count);

            System.out.println(
                    i + " -> " + chain
                    + " count=" + count
            );
        }

        double averageChainLength =
                bucketCount == 0
                        ? 0
                        : (double) totalEntries
                        / bucketCount;

        System.out.println(
                "entries=" + totalEntries
        );

        System.out.println(
                "collision=" + collisionCount
        );

        System.out.println(
                "maxChain=" + maxChain
        );

        System.out.printf(
                "averageChain=%.2f%n",
                averageChainLength
        );
    }

    public static void main(String[] args) {

        List<Integer> studentIds =
                List.of(
                        411000001,
                        411000006,
                        411000011,
                        411000016,
                        411000021,
                        411000026,
                        411000031,
                        411000036,
                        411000041,
                        411000046
                );

        analyze(studentIds, 5);

        System.out.println();

        analyze(studentIds, 7);
    }
}