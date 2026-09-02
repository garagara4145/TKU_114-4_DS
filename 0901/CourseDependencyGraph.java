import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {

    private final Map<String, Set<String>> outgoing =
            new LinkedHashMap<>();

    public boolean addCourse(String course) {
        if (course == null || course.isBlank()) {
            return false;
        }

        course = course.trim();

        return outgoing.putIfAbsent(
                course,
                new LinkedHashSet<>()
        ) == null;
    }

    public boolean addDependency(
            String prerequisite,
            String course) {

        if (!outgoing.containsKey(prerequisite)
                || !outgoing.containsKey(course)) {
            return false;
        }

        if (prerequisite.equals(course)) {
            return false;
        }

        return outgoing
                .get(prerequisite)
                .add(course);
    }

    public boolean removeDependency(
            String prerequisite,
            String course) {

        if (!outgoing.containsKey(prerequisite)
                || !outgoing.containsKey(course)) {
            return false;
        }

        return outgoing
                .get(prerequisite)
                .remove(course);
    }

    public List<String> prerequisites(String course) {

        if (!outgoing.containsKey(course)) {
            return List.of();
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry
                : outgoing.entrySet()) {

            if (entry.getValue().contains(course)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public List<String> nextCourses(String course) {

        if (!outgoing.containsKey(course)) {
            return List.of();
        }

        return new ArrayList<>(
                outgoing.get(course)
        );
    }

    public int outDegree(String course) {

        if (!outgoing.containsKey(course)) {
            return 0;
        }

        return outgoing.get(course).size();
    }

    public int inDegree(String course) {

        if (!outgoing.containsKey(course)) {
            return 0;
        }

        int count = 0;

        for (Set<String> next :
                outgoing.values()) {

            if (next.contains(course)) {
                count++;
            }
        }

        return count;
    }

    public int edgeCount() {

        int count = 0;

        for (Set<String> next :
                outgoing.values()) {

            count += next.size();
        }

        return count;
    }

    public void printReport() {

        for (String course : outgoing.keySet()) {

            System.out.println(
                    course
                    + " prerequisites="
                    + prerequisites(course)
                    + ", next="
                    + nextCourses(course)
                    + ", in="
                    + inDegree(course)
                    + ", out="
                    + outDegree(course)
            );
        }
    }

    public static void main(String[] args) {

        CourseDependencyGraph graph =
                new CourseDependencyGraph();

        graph.addCourse("Programming");
        graph.addCourse("DataStructure");
        graph.addCourse("Algorithm");
        graph.addCourse("Database");
        graph.addCourse("AI");

        graph.addDependency(
                "Programming",
                "DataStructure"
        );

        graph.addDependency(
                "DataStructure",
                "Algorithm"
        );

        graph.addDependency(
                "Database",
                "AI"
        );

        graph.addDependency(
                "Algorithm",
                "AI"
        );

        graph.addDependency(
                "DataStructure",
                "AI"
        );

        System.out.println("--- course dependency graph ---");
        graph.printReport();

        System.out.println();

        System.out.println(
                "DataStructure prerequisites="
                + graph.prerequisites("DataStructure")
        );

        System.out.println(
                "DataStructure next="
                + graph.nextCourses("DataStructure")
        );

        System.out.println(
                "AI in-degree="
                + graph.inDegree("AI")
        );

        System.out.println(
                "Programming out-degree="
                + graph.outDegree("Programming")
        );

        System.out.println(
                "edge count="
                + graph.edgeCount()
        );
    }
}