import java.util.Arrays;

class CircularQueue<T> {
    private final Object[] data;
    private int front;
    private int rear;
    private int size;

    CircularQueue(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    boolean enqueue(T value) {
        if (isFull()) {
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        return value;
    }

    @SuppressWarnings("unchecked")
    T peek() {
        if (isEmpty()) {
            return null;
        }

        return (T) data[front];
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == data.length;
    }

    int size() {
        return size;
    }

    void printState() {
        System.out.println(
                Arrays.toString(data)
                + " front=" + front
                + " rear=" + rear
                + " size=" + size);
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue =
                new CircularQueue<>(4);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        queue.dequeue();
        queue.dequeue();

        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");

        queue.dequeue();
        queue.enqueue("G");

        queue.printState();

        System.out.println("FIFO:");

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        System.out.println("size=" + queue.size());
        System.out.println("empty=" + queue.isEmpty());
    }
}