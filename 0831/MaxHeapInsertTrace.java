import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private final List<Integer> data = new ArrayList<>();

    public void add(int value) {
        data.add(value);

        int index = data.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (data.get(parent) >= data.get(index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }

        System.out.println("add=" + value + " heap=" + data);
    }

    public int peekMax() {
        if (data.isEmpty()) {
            throw new java.util.NoSuchElementException("heap is empty");
        }

        return data.get(0);
    }

    public List<Integer> snapshot() {
        return List.copyOf(data);
    }

    private void swap(int first, int second) {
        int temp = data.get(first);
        data.set(first, data.get(second));
        data.set(second, temp);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace heap = new MaxHeapInsertTrace();

        int[] values = {25, 40, 10, 50, 30, 50};

        for (int value : values) {
            heap.add(value);
        }

        System.out.println("heap=" + heap.snapshot());
        System.out.println("max=" + heap.peekMax());
    }
}