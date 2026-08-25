class ArrayStack<T> {
    private final Object[] data;
    private int size;

    ArrayStack(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    boolean push(T value) {
        if (isFull()) {
            return false;
        }

        data[size] = value;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    T pop() {
        if (isEmpty()) {
            return null;
        }

        size--;
        T value = (T) data[size];
        data[size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    T peek() {
        if (isEmpty()) {
            return null;
        }

        return (T) data[size - 1];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == data.length;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<String> names = new ArrayStack<>(3);

        System.out.println("push Amy=" + names.push("Amy"));
        System.out.println("push Ben=" + names.push("Ben"));
        System.out.println("push Cara=" + names.push("Cara"));
        System.out.println("push David=" + names.push("David"));

        System.out.println("peek=" + names.peek());
        System.out.println("size=" + names.size());
        System.out.println("full=" + names.isFull());

        System.out.println("pop=" + names.pop());
        System.out.println("pop=" + names.pop());
        System.out.println("peek=" + names.peek());

        ArrayStack<Integer> numbers = new ArrayStack<>(2);

        System.out.println("push 100=" + numbers.push(100));
        System.out.println("push 200=" + numbers.push(200));
        System.out.println("push 300=" + numbers.push(300));

        System.out.println("number peek=" + numbers.peek());
        System.out.println("number pop=" + numbers.pop());
        System.out.println("number pop=" + numbers.pop());
        System.out.println("number pop=" + numbers.pop());
        System.out.println("number empty=" + numbers.isEmpty());
    }
}