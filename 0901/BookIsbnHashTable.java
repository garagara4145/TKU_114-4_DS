import java.util.ArrayList;
import java.util.List;

public class BookIsbnHashTable {

    private static class Entry {
        String isbn;
        String title;

        Entry(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        @Override
        public String toString() {
            return isbn + "=" + title;
        }
    }

    private List<List<Entry>> buckets;
    private int size;

    public BookIsbnHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }

        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("isbn");
        }

        return Math.floorMod(
                isbn.trim().hashCode(),
                buckets.size()
        );
    }

    public void put(String isbn, String title) {

        int index = index(isbn);

        List<Entry> chain = buckets.get(index);

        for (Entry entry : chain) {

            if (entry.isbn.equals(isbn.trim())) {
                entry.title = title;
                return;
            }
        }

        chain.add(
                new Entry(
                        isbn.trim(),
                        title
                )
        );

        size++;
    }

    public String get(String isbn) {

        int index = index(isbn);

        for (Entry entry : buckets.get(index)) {

            if (entry.isbn.equals(isbn.trim())) {
                return entry.title;
            }
        }

        return null;
    }

    public boolean remove(String isbn) {

        int index = index(isbn);

        List<Entry> chain = buckets.get(index);

        for (int i = 0; i < chain.size(); i++) {

            if (chain.get(i).isbn.equals(isbn.trim())) {

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

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public void printBuckets() {

        for (int i = 0; i < buckets.size(); i++) {

            System.out.println(
                    i + " -> " + buckets.get(i)
            );
        }
    }

    public static void main(String[] args) {

        BookIsbnHashTable table =
                new BookIsbnHashTable(5);

        table.put(
                "978001",
                "Java A"
        );

        table.put(
                "978002",
                "B"
        );

        table.put(
                "978003",
                "C"
        );

        table.put(
                "978001",
                "d"
        );

        System.out.println(
                "size=" + table.size()
        );

        System.out.printf(
                "load=%.2f%n",
                table.loadFactor()
        );

        System.out.println(
                "get 978001="
                        + table.get("978001")
        );

        System.out.println(
                "get 978999="
                        + table.get("978999")
        );

        System.out.println(
                "remove 978002="
                        + table.remove("978002")
        );

        System.out.println(
                "size=" + table.size()
        );

        System.out.println(
                "--- buckets ---"
        );

        table.printBuckets();
    }
}