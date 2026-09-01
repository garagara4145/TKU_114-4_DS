import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void report(List<Integer> keys, int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }

        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null) {
            for (Integer key : keys) {

                if (key == null) {
                    continue;
                }

                int index =
                        Math.floorMod(
                                Integer.hashCode(key),
                                bucketCount
                        );

                buckets.get(index).add(key);
            }
        }

        int collisionCount = 0;
        int maxChain = 0;

        for (int i = 0; i < buckets.size(); i++) {

            List<Integer> bucket = buckets.get(i);

            System.out.println(
                    i + " -> " + bucket
            );

            if (bucket.size() > 1) {
                collisionCount += bucket.size() - 1;
            }

            maxChain =
                    Math.max(maxChain, bucket.size());
        }

        System.out.println(
                "collision=" + collisionCount
        );

        System.out.println(
                "maxChain=" + maxChain
        );
    }

    public static void main(String[] args) {

        List<Integer> keys =
                List.of(
                        12,
                        7,
                        22,
                        -3,
                        7
                );

        report(keys, 5);

        System.out.println("--- empty ---");

        report(List.of(), 5);
    }
}