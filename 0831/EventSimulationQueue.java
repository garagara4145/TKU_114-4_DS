import java.util.Comparator;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    record Event(String id, int time, String type, long sequence) {
    }

    private final PriorityQueue<Event> queue;

    public EventSimulationQueue() {
        Comparator<Event> order = Comparator
                .comparingInt(Event::time)
                .thenComparingLong(Event::sequence)
                .thenComparing(Event::id);

        queue = new PriorityQueue<>(order);
    }

    public void addEvent(
            String id,
            int time,
            String type,
            long sequence) {

        queue.offer(
                new Event(id, time, type, sequence)
        );
    }

    public boolean cancel(String id) {
        return queue.removeIf(event ->
                event.id().equals(id));
    }

    public Event nextEvent() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {

        EventSimulationQueue simulator =
                new EventSimulationQueue();

        simulator.addEvent("E1", 10, "START", 1);
        simulator.addEvent("E2", 5, "LOGIN", 2);
        simulator.addEvent("E3", 10, "UPDATE", 3);
        simulator.addEvent("E4", 5, "PAYMENT", 4);
        simulator.addEvent("E5", 15, "END", 5);

        System.out.println(
                "cancel E3=" + simulator.cancel("E3")
        );

        System.out.println("--- execution ---");

        Event event;

        while ((event = simulator.nextEvent()) != null) {

            System.out.println(
                    event.id()
                    + "|" + event.time()
                    + "|" + event.type()
                    + "|" + event.sequence()
            );
        }

        System.out.println(
                "remaining=" + simulator.size()
        );
    }
}