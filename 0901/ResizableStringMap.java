import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {

    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private List<List<Entry>> buckets;
    private int size;

    public ResizableStringMap(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }

        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }

        return Math.floorMod(key.hashCode(), buckets.size());
    }

    public void put(String key, String value) {
        int index = index(key);
        List<Entry> chain = buckets.get(index);

        for (Entry entry : chain) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        chain.add(new Entry(key, value));
        size++;

        if (loadFactor() > 0.75) {
            rehash();
        }
    }

    public String get(String key) {
        int index = index(key);

        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(String key) {
        int index = index(key);
        List<Entry> chain = buckets.get(index);

        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)) {
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

    private void rehash() {
        int newBucketCount = buckets.size() * 2 + 1;

        List<List<Entry>> oldBuckets = buckets;

        buckets = new ArrayList<>();

        for (int i = 0; i < newBucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (List<Entry> chain : oldBuckets) {
            for (Entry entry : chain) {
                int newIndex = index(entry.key);
                buckets.get(newIndex).add(entry);
            }
        }
    }

    public void printBuckets() {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println(i + " -> " + buckets.get(i));
        }
    }

    public static void main(String[] args) {

        ResizableStringMap map = new ResizableStringMap(3);

        map.put("A", "Apple");
        map.put("B", "Banana");
        map.put("C", "Cat");

        System.out.println("size=" + map.size());
        System.out.printf("load=%.2f%n", map.loadFactor());

        map.put("D", "Dog");

        System.out.println("size=" + map.size());
        System.out.printf("load=%.2f%n", map.loadFactor());

        System.out.println("get A=" + map.get("A"));
        System.out.println("get D=" + map.get("D"));

        System.out.println("remove B=" + map.remove("B"));
        System.out.println("size=" + map.size());

        map.printBuckets();
    }
}