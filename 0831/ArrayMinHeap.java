import java.util.Arrays;

public class ArrayMinHeap {

    private int[] data;
    private int size;

    public ArrayMinHeap() {
        data = new int[4];
        size = 0;
    }

    public void add(int value) {

        if (size == data.length) {
            grow();
        }

        data[size] = value;

        int index = size;
        size++;

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (data[parent] <= data[index]) {
                break;
            }

            swap(parent, index);

            index = parent;
        }
    }

    public int peek() {

        if (size == 0) {
            throw new java.util.NoSuchElementException(
                    "heap is empty"
            );
        }

        return data[0];
    }

    public int remove() {

        if (size == 0) {
            throw new java.util.NoSuchElementException(
                    "heap is empty"
            );
        }

        int result = data[0];

        data[0] = data[size - 1];

        size--;

        if (size > 0) {
            bubbleDown(0);
        }

        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    private void bubbleDown(int index) {

        while (true) {

            int left = index * 2 + 1;
            int right = index * 2 + 2;

            if (left >= size) {
                return;
            }

            int smaller = left;

            if (right < size
                    && data[right] < data[left]) {
                smaller = right;
            }

            if (data[index] <= data[smaller]) {
                return;
            }

            swap(index, smaller);

            index = smaller;
        }
    }

    private void grow() {

        int[] newData = new int[data.length * 2];

        System.arraycopy(
                data,
                0,
                newData,
                0,
                size
        );

        data = newData;
    }

    private void swap(int first, int second) {

        int temp = data[first];

        data[first] = data[second];

        data[second] = temp;
    }

    public static void main(String[] args) {

        ArrayMinHeap heap = new ArrayMinHeap();

        int[] values = {
                50, 20, 80, 10, 40,
                30, 70, 60, 90, 15,
                25, 35, 45, 55, 65,
                75, 85, 95, 5, 100
        };

        for (int value : values) {
            heap.add(value);
        }

        System.out.println(
                "size=" + heap.size()
        );

        System.out.println(
                "peek=" + heap.peek()
        );

        System.out.println(
                "heap=" + Arrays.toString(
                        heap.snapshot()
                )
        );

        System.out.println("--- remove ---");

        while (!heap.isEmpty()) {

            System.out.println(
                    "remove=" + heap.remove()
            );
        }

        System.out.println(
                "empty=" + heap.isEmpty()
        );
    }
}