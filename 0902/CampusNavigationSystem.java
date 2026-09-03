import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusNavigationSystem {

    private Map<String, String> locations;
    private Map<String, Set<String>> roads;

    public CampusNavigationSystem() {
        locations = new HashMap<>();
        roads = new HashMap<>();
    }

    public boolean addLocation(String id, String name) {
        if (id == null || name == null) {
            return false;
        }

        id = id.trim();
        name = name.trim();

        if (id.isEmpty()
                || name.isEmpty()
                || locations.containsKey(id)) {
            return false;
        }

        locations.put(id, name);
        roads.put(id, new LinkedHashSet<>());

        return true;
    }

    public boolean addRoad(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!locations.containsKey(from)
                || !locations.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        if (roads.get(from).contains(to)) {
            return false;
        }

        roads.get(from).add(to);
        roads.get(to).add(from);

        return true;
    }

    public List<String> shortestPath(
            String start,
            String target) {

        List<String> path =
                new ArrayList<>();

        if (start == null
                || target == null
                || !locations.containsKey(start)
                || !locations.containsKey(target)) {
            return path;
        }

        if (start.equals(target)) {
            path.add(start);
            return path;
        }

        Queue<String> queue =
                new ArrayDeque<>();

        Set<String> visited =
                new java.util.HashSet<>();

        Map<String, String> previous =
                new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            for (String next :
                    roads.getOrDefault(
                            current,
                            Set.of())) {

                if (visited.add(next)) {

                    previous.put(next, current);
                    queue.offer(next);

                    if (next.equals(target)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if (!visited.contains(target)) {
            return path;
        }

        String current = target;

        while (current != null) {

            path.add(0, current);

            if (current.equals(start)) {
                break;
            }

            current = previous.get(current);
        }

        return path;
    }

    public int edgeCount(
            List<String> path) {

        if (path == null
                || path.size() <= 1) {
            return 0;
        }

        return path.size() - 1;
    }

    public String locationName(String id) {
        if (id == null) {
            return null;
        }

        return locations.get(id.trim());
    }

    public static void main(String[] args) {

        CampusNavigationSystem campus =
                new CampusNavigationSystem();

        System.out.println(
                "新增A  "
                        + campus.addLocation(
                                "A",
                                "圖書館"));

        System.out.println(
                "新增 B  "
                        + campus.addLocation(
                                "B",
                                "教學大樓"));

        System.out.println(
                "新增C  "
                        + campus.addLocation(
                                "C",
                                "學生活動中心"));

        System.out.println(
                "新增 D  "
                        + campus.addLocation(
                                "D",
                                "體育館"));

        System.out.println(
                "新增E  "
                        + campus.addLocation(
                                "E",
                                "宿舍"));

        System.out.println(
                "新增A to B "
                        + campus.addRoad("A", "B"));

        System.out.println(
                "新增A to C  "
                        + campus.addRoad("A", "C"));

        System.out.println(
                "新增B to D "
                        + campus.addRoad("B", "D"));

        System.out.println(
                "新增C to D  "
                        + campus.addRoad("C", "D"));

        System.out.println(
                "新增D to E  "
                        + campus.addRoad("D", "E"));

        System.out.println(
                "A 的名稱 = "
                        + campus.locationName("A"));

        List<String> path =
                campus.shortestPath("A", "E");

        System.out.println(
                "A to E 的最短路徑 = "
                        + path);

        System.out.println(
                "A to E 的邊數 = "
                        + campus.edgeCount(path));

        path = campus.shortestPath("A", "D");

        System.out.println(
                "A to D 的最短路徑 = "
                        + path);

        System.out.println(
                "A to D 的邊數 "
                        + campus.edgeCount(path));

        path = campus.shortestPath("A", "A");

        System.out.println(
                "A to A 的最短路徑 "
                        + path);

        System.out.println(
                "A to A 的邊數  "
                        + campus.edgeCount(path));

        path = campus.shortestPath("A", "Z");

        System.out.println(
                "A to Z 的最短路徑  "
                        + path);

        System.out.println(
                "不存在Z "
                        + campus.locationName("Z"));

        System.out.println(
                "重複新增 A "
                        + campus.addLocation(
                                "A",
                                "new id"));

        System.out.println(
                "新增不存在A to Z  "
                        + campus.addRoad("A", "Z"));

        System.out.println(
                "重複新增 A to B "
                        + campus.addRoad("A", "B"));
    }
}