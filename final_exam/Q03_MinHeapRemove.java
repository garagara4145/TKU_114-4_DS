import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {

    private ArrayList<Integer> heap;

    public Q03_MinHeapRemove(List<Integer> values) {
        heap = new ArrayList<>();

        if (values != null) {
            for (Integer value : values) {
                if (value != null) {
                    heap.add(value);
                }
            }
        }

        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < heap.size()
                    && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < heap.size()
                    && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            int temp = heap.get(index);
            heap.set(index, heap.get(smallest));
            heap.set(smallest, temp);

            index = smallest;
        }
    }

    public Integer removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        int min = heap.get(0);

        if (heap.size() == 1) {
            heap.remove(0);
            return min;
        }

        int last = heap.remove(heap.size() - 1);
        heap.set(0, last);

        bubbleDown(0);

        return min;
    }

    public Integer peek() {
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(3);
        values.add(6);
        values.add(null);
        values.add(1);
        values.add(5);
        values.add(3);

        Q03_MinHeapRemove h = new Q03_MinHeapRemove(values);

        System.out.println("Heap = " + h.snapshot());
        System.out.println("最小值" + h.peek());
        System.out.println("數量" + h.size());

        System.out.println("移除最小值" + h.removeMin());
        System.out.println("Heap" + h.snapshot());

        System.out.println("移除最小值" + h.removeMin());
        System.out.println("Heap" + h.snapshot());

        System.out.println("移除最小值" + h.removeMin());
        System.out.println("Heap" + h.snapshot());

        Q03_MinHeapRemove h2 =
                new Q03_MinHeapRemove(List.of(1));

        System.out.println("單一元素移除" + h2.removeMin());
        System.out.println("單一元素移除" + h2.removeMin());

        Q03_MinHeapRemove h3 =
                new Q03_MinHeapRemove(null);

        System.out.println("空白移除 " + h3.removeMin());
        System.out.println("最小值" + h3.peek());
        System.out.println("資料數量" + h3.size());
    }
}