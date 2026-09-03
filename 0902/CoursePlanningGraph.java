import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {

    private Map<String, Set<String>> graph;

    public CoursePlanningGraph() {
        graph = new LinkedHashMap<>();
    }

    public boolean addCourse(String course) {

        if (course == null) {
            return false;
        }

        course = course.trim();

        if (course.isEmpty()
                || graph.containsKey(course)) {
            return false;
        }

        graph.put(
                course,
                new LinkedHashSet<>());

        return true;
    }

    public boolean addPrerequisite(
            String prerequisite,
            String course) {

        if (prerequisite == null
                || course == null) {
            return false;
        }

        prerequisite = prerequisite.trim();
        course = course.trim();

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)) {
            return false;
        }

        if (prerequisite.equals(course)) {
            return false;
        }

        return graph.get(prerequisite)
                .add(course);
    }

    public boolean reachable(
            String start,
            String target) {

        if (start == null
                || target == null) {
            return false;
        }

        start = start.trim();
        target = target.trim();

        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return false;
        }

        Set<String> visited =
                new HashSet<>();

        return reachableDfs(
                start,
                target,
                visited);
    }

    private boolean reachableDfs(
            String current,
            String target,
            Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (String next :
                graph.getOrDefault(
                        current,
                        Set.of())) {

            if (!visited.contains(next)) {

                if (reachableDfs(
                        next,
                        target,
                        visited)) {

                    return true;
                }
            }
        }

        return false;
    }

    public List<String> affectedCourses(
            String course) {

        List<String> result =
                new ArrayList<>();

        if (course == null) {
            return result;
        }

        course = course.trim();

        if (!graph.containsKey(course)) {
            return result;
        }

        Set<String> visited =
                new HashSet<>();

        affectedDfs(
                course,
                course,
                visited,
                result);

        return result;
    }

    private void affectedDfs(
            String current,
            String start,
            Set<String> visited,
            List<String> result) {

        visited.add(current);

        for (String next :
                graph.getOrDefault(
                        current,
                        Set.of())) {

            if (!visited.contains(next)) {

                result.add(next);

                affectedDfs(
                        next,
                        start,
                        visited,
                        result);
            }
        }
    }

    public static void main(String[] args) {

        CoursePlanningGraph planning =
                new CoursePlanningGraph();

        System.out.println(
                "新增課程 A = "
                        + planning.addCourse("A"));

        System.out.println(
                "新增課程 B = "
                        + planning.addCourse("B"));

        System.out.println(
                "新增課程 C = "
                        + planning.addCourse("C"));

        System.out.println(
                "新增課程 D = "
                        + planning.addCourse("D"));

        System.out.println(
                "新增課程 E = "
                        + planning.addCourse("E"));

        System.out.println(
                "新增課程 F = "
                        + planning.addCourse("F"));

        System.out.println(
                "新增先修 A → B = "
                        + planning.addPrerequisite(
                                "A", "B"));

        System.out.println(
                "新增先修 A → C = "
                        + planning.addPrerequisite(
                                "A", "C"));

        System.out.println(
                "新增先修 B → D = "
                        + planning.addPrerequisite(
                                "B", "D"));

        System.out.println(
                "新增先修 B → E = "
                        + planning.addPrerequisite(
                                "B", "E"));

        System.out.println(
                "新增先修 C → F = "
                        + planning.addPrerequisite(
                                "C", "F"));

        System.out.println(
                "A 可以到 D = "
                        + planning.reachable(
                                "A", "D"));

        System.out.println(
                "A 可以到 F = "
                        + planning.reachable(
                                "A", "F"));

        System.out.println(
                "D 可以到 A = "
                        + planning.reachable(
                                "D", "A"));

        System.out.println(
                "A 可以到 A = "
                        + planning.reachable(
                                "A", "A"));

        System.out.println(
                "A 的受影響課程 = "
                        + planning.affectedCourses(
                                "A"));

        System.out.println(
                "B 的受影響課程 = "
                        + planning.affectedCourses(
                                "B"));

        System.out.println(
                "F 的受影響課程 = "
                        + planning.affectedCourses(
                                "F"));

        System.out.println(
                "不存在的 G = "
                        + planning.affectedCourses(
                                "G"));

        System.out.println(
                "重複新增 A = "
                        + planning.addCourse("A"));

        System.out.println(
                "重複先修 A → B = "
                        + planning.addPrerequisite(
                                "A", "B"));

        System.out.println(
                "自己先修 A → A = "
                        + planning.addPrerequisite(
                                "A", "A"));

        System.out.println(
                "不存在先修 A → G = "
                        + planning.addPrerequisite(
                                "A", "G"));
    }
}