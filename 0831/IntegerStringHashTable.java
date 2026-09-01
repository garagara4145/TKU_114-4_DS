import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {

    private record Entry(int key, String value) {
    }

    private final List<List<Entry>> buckets;

    private int size;

    public IntegerStringHashTable(int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException(
                    "bucketCount"
            );
        }

        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(int key) {

        return Math.floorMod(
                Integer.hashCode(key),
                buckets.size()
        );
    }

    public void put(int key, String value) {

        List<Entry> chain =
                buckets.get(index(key));

        for (int i = 0; i < chain.size(); i++) {

            Entry entry = chain.get(i);

            if (entry.key() == key) {

                chain.set(
                        i,
                        new Entry(key, value)
                );

                return;
            }
        }

        chain.add(
                new Entry(key, value)
        );

        size++;
    }

    public String get(int key) {

        List<Entry> chain =
                buckets.get(index(key));

        for (Entry entry : chain) {

            if (entry.key() == key) {
                return entry.value();
            }
        }

        return null;
    }

    public boolean containsKey(int key) {

        return get(key) != null;
    }

    public boolean remove(int key) {

        List<Entry> chain =
                buckets.get(index(key));

        for (int i = 0; i < chain.size(); i++) {

            if (chain.get(i).key() == key) {

                chain.remove(i);

                size--;

                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {

        for (int i = 0; i < buckets.size(); i++) {

            System.out.println(
                    i + " -> " + buckets.get(i)
            );
        }
    }

    public static void main(String[] args) {

        IntegerStringHashTable table =
                new IntegerStringHashTable(5);

        table.put(12, "A");
        table.put(7, "B");
        table.put(22, "C");
        table.put(-3, "D");

        System.out.println(
                "size=" + table.size()
        );

        System.out.println(
                "get7=" + table.get(7)
        );

        table.put(7, "B2");

        System.out.println(
                "get7=" + table.get(7)
        );

        System.out.println(
                "size after update=" + table.size()
        );

        System.out.println(
                "contains22="
                + table.containsKey(22)
        );

        System.out.println(
                "remove12="
                + table.remove(12)
        );

        System.out.println(
                "size after remove="
                + table.size()
        );

        System.out.println("--- buckets ---");

        table.bucketReport();
    }
}