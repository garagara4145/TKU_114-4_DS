import java.util.ArrayDeque;
import java.util.Deque;

class QueueCustomer {
    private final int id;
    private final String name;

    QueueCustomer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class WaitingQueue {
    private final Deque<QueueCustomer> queue = new ArrayDeque<>();

    boolean add(QueueCustomer customer) {
        if (customer == null) {
            return false;
        }

        queue.offerLast(customer);
        return true;
    }

    QueueCustomer peekNext() {
        return queue.peekFirst();
    }

    QueueCustomer serveNext() {
        return queue.pollFirst();
    }

    int size() {
        return queue.size();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }

    void printQueue() {
        System.out.println("等候隊列=" + queue);
        System.out.println("等候人數=" + queue.size());
    }
}

public class CounterWaitingQueue {
    public static void main(String[] args) {
        WaitingQueue waitingQueue = new WaitingQueue();

        System.out.println("空隊列下一位=" + waitingQueue.peekNext());

        System.out.println("加入1=" +
                waitingQueue.add(new QueueCustomer(1, "Amy")));

        System.out.println("加入2=" +
                waitingQueue.add(new QueueCustomer(2, "Ben")));

        System.out.println("加入3=" +
                waitingQueue.add(new QueueCustomer(3, "Cara")));

        waitingQueue.printQueue();

        System.out.println("下一位=" + waitingQueue.peekNext());

        System.out.println("服務=" + waitingQueue.serveNext());

        waitingQueue.printQueue();

        System.out.println("下一位=" + waitingQueue.peekNext());

        System.out.println("服務=" + waitingQueue.serveNext());

        System.out.println("服務=" + waitingQueue.serveNext());

        System.out.println("空隊列服務=" + waitingQueue.serveNext());

        waitingQueue.printQueue();

        System.out.println("是否為空=" + waitingQueue.isEmpty());
    }
}