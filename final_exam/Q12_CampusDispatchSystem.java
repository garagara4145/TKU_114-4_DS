import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public class Q12_CampusDispatchSystem {

    public record Request(
            String id,
            String location,
            int priority,
            long sequence) {
    }

    private Map<String, Set<String>> roads;
    private Map<String, Request> requests;
    private PriorityQueue<Request> queue;

    public Q12_CampusDispatchSystem() {
        roads = new HashMap<>();
        requests = new HashMap<>();

        queue = new PriorityQueue<>(
                (a, b) -> {
                    if (a.priority() != b.priority()) {
                        return Integer.compare(
                                a.priority(),
                                b.priority());
                    }

                    return Long.compare(
                            a.sequence(),
                            b.sequence());
                });
    }

    public boolean addLocation(String location) {
        if (location == null
                || location.trim().isEmpty()
                || roads.containsKey(location)) {
            return false;
        }

        roads.put(
                location,
                new LinkedHashSet<>());

        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null
                || second == null
                || !roads.containsKey(first)
                || !roads.containsKey(second)) {
            return false;
        }

        if (first.equals(second)) {
            return false;
        }

        if (roads.get(first).contains(second)) {
            return false;
        }

        roads.get(first).add(second);
        roads.get(second).add(first);

        return true;
    }

    public boolean submit(Request request) {
        if (request == null
                || request.id() == null
                || request.location() == null) {
            return false;
        }

        String id = request.id().trim();
        String location = request.location().trim();

        if (id.isEmpty()
                || location.isEmpty()
                || !roads.containsKey(location)) {
            return false;
        }

        if (requests.containsKey(id)) {
            return false;
        }

        Request newRequest = new Request(
                id,
                location,
                request.priority(),
                request.sequence());

        requests.put(id, newRequest);
        queue.offer(newRequest);

        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null
                || !roads.containsKey(serviceCenter)) {
            return null;
        }

        List<Request> unreachable = new ArrayList<>();

        while (!queue.isEmpty()) {
            Request request = queue.poll();

            if (isReachable(
                    serviceCenter,
                    request.location())) {

                requests.remove(request.id());

                for (Request item : unreachable) {
                    queue.offer(item);
                }

                return request;
            }

            unreachable.add(request);
        }

        for (Request item : unreachable) {
            queue.offer(item);
        }

        return null;
    }

    private boolean isReachable(
            String start,
            String target) {

        if (start.equals(target)) {
            return true;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : roads.get(current)) {

                if (!visited.contains(next)) {

                    if (next.equals(target)) {
                        return true;
                    }

                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return false;
    }

    public List<String> route(
            String start,
            String target) {

        List<String> result = new ArrayList<>();

        if (start == null
                || target == null
                || !roads.containsKey(start)
                || !roads.containsKey(target)) {
            return result;
        }

        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : roads.get(current)) {

                if (!visited.contains(next)) {

                    visited.add(next);
                    predecessor.put(next, current);
                    queue.offer(next);

                    if (next.equals(target)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if (!visited.contains(target)) {
            return result;
        }

        String current = target;

        while (current != null) {
            result.add(0, current);

            if (current.equals(start)) {
                break;
            }

            current = predecessor.get(current);
        }

        return result;
    }

    public int pendingCount() {
        return requests.size();
    }

    public static void main(String[] args) {

        Q12_CampusDispatchSystem system =
                new Q12_CampusDispatchSystem();

        System.out.println("新增A  "
                + system.addLocation("A"));

        System.out.println("新增B  "
                + system.addLocation("B"));

        System.out.println("新增C  "
                + system.addLocation("C"));

        System.out.println("新增D  "
                + system.addLocation("D"));

        System.out.println("新增E  "
                + system.addLocation("E"));

        System.out.println("新增A to B  "
                + system.addRoad("A", "B"));

        System.out.println("新增A to C  "
                + system.addRoad("A", "C"));

        System.out.println("新增B to D  "
                + system.addRoad("B", "D"));

        System.out.println("新增C to D  "
                + system.addRoad("C", "D"));

        System.out.println("新增D to E  "
                + system.addRoad("D", "E"));

        System.out.println("A to E  "
                + system.route("A", "E"));

        System.out.println("提交請求 A = "
                + system.submit(
                        new Request("A", "D", 2, 1)));

        System.out.println("提交請求 B = "
                + system.submit(
                        new Request("B", "E", 1, 2)));

        System.out.println("提交請求 C = "
                + system.submit(
                        new Request("C", "C", 1, 3)));

        System.out.println("A重複  "
                + system.submit(
                        new Request("A", "B", 1, 4)));

        System.out.println("待處理 "
                + system.pendingCount());

        Request next =
                system.nextReachable("A");

        System.out.println("下一個 "
                + next);

        System.out.println("剩餘  "
                + system.pendingCount());

        next = system.nextReachable("A");

        System.out.println("下一個 "
                + next);

        System.out.println("剩餘 "
                + system.pendingCount());

        System.out.println("A to A  "
                + system.route("A", "A"));

        System.out.println("A to Z  "
                + system.route("A", "Z"));

        System.out.println("無  "
                + system.nextReachable("Z"));
    }
}