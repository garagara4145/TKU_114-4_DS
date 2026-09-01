import java.util.Comparator;
import java.util.PriorityQueue;

public class SupportTicketQueue {

    record Ticket(String id, int severity, long createdOrder) {
    }

    public static void main(String[] args) {

        Comparator<Ticket> order = Comparator
                .comparingInt(Ticket::severity)
                .reversed()
                .thenComparingLong(Ticket::createdOrder)
                .thenComparing(Ticket::id);

        PriorityQueue<Ticket> queue =
                new PriorityQueue<>(order);

        queue.offer(new Ticket("T001", 3, 1));
        queue.offer(new Ticket("T002", 5, 2));
        queue.offer(new Ticket("T003", 5, 3));
        queue.offer(new Ticket("T004", 2, 4));
        queue.offer(new Ticket("T005", 5, 5));

        while (!queue.isEmpty()) {
            Ticket ticket = queue.poll();

            System.out.println(
                    ticket.id()
                    + "|" + ticket.severity()
                    + "|" + ticket.createdOrder()
            );
        }
    }
}