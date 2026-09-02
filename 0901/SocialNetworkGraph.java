import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    private final Map<String, Set<String>> friends =
            new LinkedHashMap<>();

    public boolean addUser(String user) {
        if (user == null || user.isBlank()) {
            return false;
        }

        user = user.trim();

        return friends.putIfAbsent(
                user,
                new LinkedHashSet<>()
        ) == null;
    }

    public boolean addFriend(String first, String second) {

        if (!friends.containsKey(first)
                || !friends.containsKey(second)) {
            return false;
        }

        if (first.equals(second)) {
            return false;
        }

        boolean changed = friends.get(first).add(second);

        friends.get(second).add(first);

        return changed;
    }

    public boolean removeFriend(String first, String second) {

        if (!friends.containsKey(first)
                || !friends.containsKey(second)) {
            return false;
        }

        boolean changed = friends.get(first).remove(second);

        friends.get(second).remove(first);

        return changed;
    }

    public List<String> friendsOf(String user) {

        if (!friends.containsKey(user)) {
            return List.of();
        }

        return new ArrayList<>(friends.get(user));
    }

    public Set<String> mutualFriends(
            String first,
            String second) {

        if (!friends.containsKey(first)
                || !friends.containsKey(second)) {
            return Set.of();
        }

        Set<String> result =
                new LinkedHashSet<>(friends.get(first));

        result.retainAll(friends.get(second));

        return result;
    }

    public List<String> isolatedUsers() {

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry
                : friends.entrySet()) {

            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public int userCount() {
        return friends.size();
    }

    public int edgeCount() {

        int degreeSum = 0;

        for (Set<String> userFriends : friends.values()) {
            degreeSum += userFriends.size();
        }

        return degreeSum / 2;
    }

    public void printGraph() {

        for (Map.Entry<String, Set<String>> entry
                : friends.entrySet()) {

            System.out.println(
                    entry.getKey() + " -> "
                            + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        SocialNetworkGraph graph =
                new SocialNetworkGraph();

        graph.addUser("Alice");
        graph.addUser("Bob");
        graph.addUser("Charlie");
        graph.addUser("David");

        graph.addFriend("Alice", "Bob");
        graph.addFriend("Alice", "Charlie");
        graph.addFriend("Bob", "Charlie");

        graph.addFriend("Alice", "Bob");

        System.out.println("--- graph ---");
        graph.printGraph();

        System.out.println();

        System.out.println(
                "Alice friends="
                        + graph.friendsOf("Alice")
        );

        System.out.println(
                "mutual Alice/Bob="
                        + graph.mutualFriends(
                                "Alice",
                                "Bob"
                        )
        );

        System.out.println(
                "edge count="
                        + graph.edgeCount()
        );

        System.out.println(
                "isolated="
                        + graph.isolatedUsers()
        );

        System.out.println(
                "remove Alice-Bob="
                        + graph.removeFriend(
                                "Alice",
                                "Bob"
                        )
        );

        System.out.println();

        System.out.println("--- after remove ---");
        graph.printGraph();

        System.out.println(
                "edge count="
                        + graph.edgeCount()
        );
    }
}