import java.util.ArrayList;
import java.util.List;

public class Q04_ChainedHashTable {

    private ArrayList<List<Entry>> buckets;
    private int size;

    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        size = 0;
    }

    private int index(int key) {
        return Math.floorMod(key, buckets.size());
    }

    public void put(int key, String value) {
        int index = index(key);
        List<Entry> bucket = buckets.get(index);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int index = index(key);
        List<Entry> bucket = buckets.get(index);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(int key) {
        int index = index(key);
        List<Entry> bucket = buckets.get(index);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int max = 0;

        for (List<Entry> bucket : buckets) {
            if (bucket.size() > max) {
                max = bucket.size();
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Q04_ChainedHashTable table =
                new Q04_ChainedHashTable(3);

        table.put(0, "A");
        table.put(3, "B");
        table.put(6, "C");
        table.put(1, "D");
        table.put(-1, "E");

        System.out.println("查詢0" + table.get(0));
        System.out.println("查詢3" + table.get(3));
        System.out.println("查詢6" + table.get(6));
        System.out.println("查詢-1" + table.get(-1));

        System.out.println("數量" + table.size());
        System.out.println("長度" + table.longestChain());

        table.put(3, "F");

        System.out.println("更新後3" + table.get(3));
        System.out.println("更新後數量" + table.size());

        System.out.println("刪除 6 " + table.remove(6));
        System.out.println("刪除後數量 " + table.size());
        System.out.println("刪除後長度" + table.longestChain());

        System.out.println("刪除 100  " + table.remove(100));
        System.out.println("查詢 100  " + table.get(100));
    }
}