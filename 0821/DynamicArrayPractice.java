import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    DynamicArray(int initialCapacity) {
        data = new Object[Math.max(1, initialCapacity)];
    }

    void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    void add(int index, T value) {
        checkInsertIndex(index);
        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    T set(int index, T value) {
        checkIndex(index);

        T oldValue = (T) data[index];
        data[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    T remove(int index) {
        checkIndex(index);

        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return removed;
    }

    int size() {
        return size;
    }

    int capacity() {
        return data.length;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>(2);

        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        System.out.println("String=" + names);
        System.out.println("size=" + names.size());
        System.out.println("capacity=" + names.capacity());

        names.add(1, "David");
        System.out.println("insert=" + names);

        System.out.println("get(2)=" + names.get(2));

        System.out.println("set=" + names.set(2, "Eric"));
        System.out.println("after set=" + names);

        System.out.println("remove=" + names.remove(1));
        System.out.println("after remove=" + names);

        DynamicArray<Integer> numbers = new DynamicArray<>(2);

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        numbers.add(1, 15);

        System.out.println("Integer=" + numbers);
        System.out.println("remove=" + numbers.remove(2));
        System.out.println("after remove=" + numbers);

        try {
            names.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(-1)=IndexOutOfBoundsException");
        }

        try {
            names.get(names.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(size)=IndexOutOfBoundsException");
        }

        DynamicArray<String> empty = new DynamicArray<>(2);

        try {
            empty.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("empty remove=IndexOutOfBoundsException");
        }
    }
}