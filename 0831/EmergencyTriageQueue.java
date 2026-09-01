import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    record Patient(String medicalId, int severity, long arrivalOrder) {
    }

    private final PriorityQueue<Patient> queue;

    public EmergencyTriageQueue() {
        Comparator<Patient> order = Comparator
                .comparingInt(Patient::severity)
                .reversed()
                .thenComparingLong(Patient::arrivalOrder)
                .thenComparing(Patient::medicalId);

        queue = new PriorityQueue<>(order);
    }

    public void checkIn(String medicalId, int severity, long arrivalOrder) {
        queue.offer(new Patient(medicalId, severity, arrivalOrder));
    }

    public Patient peekNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        return queue.peek();
    }

    public Patient callNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {

        EmergencyTriageQueue queue =
                new EmergencyTriageQueue();

        queue.checkIn("M003", 3, 3);
        queue.checkIn("M001", 5, 1);
        queue.checkIn("M002", 5, 2);
        queue.checkIn("M004", 2, 4);
        queue.checkIn("M005", 5, 5);

        System.out.println("size=" + queue.size());

        Patient next = queue.peekNext();

        System.out.println(
                "next="
                + next.medicalId()
                + "|" + next.severity()
                + "|" + next.arrivalOrder()
        );

        while (!queue.isEmpty()) {
            Patient patient = queue.callNext();

            System.out.println(
                    "call="
                    + patient.medicalId()
                    + "|" + patient.severity()
                    + "|" + patient.arrivalOrder()
            );
        }

        System.out.println("size=" + queue.size());
        System.out.println("empty=" + queue.isEmpty());

        try {
            queue.peekNext();
        } catch (NoSuchElementException e) {
            System.out.println("empty peek -> NoSuchElementException");
        }

        try {
            queue.callNext();
        } catch (NoSuchElementException e) {
            System.out.println("empty call -> NoSuchElementException");
        }
    }
}