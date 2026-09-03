import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q01_PriorityRecord {

    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return new ArrayList<>();
        }

        Comparator<Job> comparator =
                Comparator.comparingInt(Job::priority)
                        .thenComparingLong(Job::sequence)
                        .thenComparing(Job::id);

        PriorityQueue<Job> queue = new PriorityQueue<>(comparator);

        for (Job job : jobs) {
            if (job != null) {
                queue.offer(job);
            }
        }

        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            result.add(queue.poll().id());
        }

        return result;
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();

        jobs.add(new Job("A", 2, 5));
        jobs.add(new Job("B", 1, 10));
        jobs.add(new Job("C", 1, 3));
        jobs.add(new Job("D", 2, 2));
        jobs.add(new Job("E", 1, 3));
        jobs.add(null);

        System.out.println(processOrder(jobs));
        System.out.println(processOrder(null));
        System.out.println(processOrder(new ArrayList<>()));
    }
}