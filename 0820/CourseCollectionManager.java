import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseEnrollment {
    private final String studentId;
    private final String name;
    private int score;
    private final Set<String> tags = new HashSet<>();

    CourseEnrollment(String studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = Math.max(0, Math.min(100, score));
    }

    String getStudentId() {
        return studentId;
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = Math.max(0, Math.min(100, score));
    }

    void addTag(String tag) {
        if (tag != null && !tag.isBlank()) {
            tags.add(tag.trim().toLowerCase());
        }
    }

    boolean hasTag(String tag) {
        return tag != null
                && !tag.isBlank()
                && tags.contains(tag.trim().toLowerCase());
    }

    @Override
    public String toString() {
        return studentId
                + " "
                + name
                + " score="
                + score
                + " tags="
                + tags;
    }
}

class CourseManager {
    private final List<CourseEnrollment> order =
            new ArrayList<>();

    private final Set<String> registeredIds =
            new HashSet<>();

    private final Map<String, CourseEnrollment> byId =
            new HashMap<>();

    boolean enroll(CourseEnrollment enrollment) {
        if (enrollment == null) {
            return false;
        }

        if (!registeredIds.add(
                enrollment.getStudentId())) {
            return false;
        }

        order.add(enrollment);

        byId.put(
                enrollment.getStudentId(),
                enrollment);

        return true;
    }

    boolean updateScore(String studentId, int score) {
        CourseEnrollment enrollment =
                byId.get(studentId);

        if (enrollment == null) {
            return false;
        }

        enrollment.setScore(score);
        return true;
    }

    List<CourseEnrollment> findByTag(String tag) {
        List<CourseEnrollment> result =
                new ArrayList<>();

        if (tag == null || tag.isBlank()) {
            return result;
        }

        for (CourseEnrollment enrollment : order) {
            if (enrollment.hasTag(tag)) {
                result.add(enrollment);
            }
        }

        return result;
    }

    Map<String, Integer> scoreDistribution() {
        Map<String, Integer> result =
                new HashMap<>();

        result.put("A", 0);
        result.put("B", 0);
        result.put("C", 0);
        result.put("D", 0);
        result.put("F", 0);

        for (CourseEnrollment enrollment : order) {
            int score = enrollment.getScore();

            if (score >= 90) {
                result.put("A", result.get("A") + 1);
            } else if (score >= 80) {
                result.put("B", result.get("B") + 1);
            } else if (score >= 70) {
                result.put("C", result.get("C") + 1);
            } else if (score >= 60) {
                result.put("D", result.get("D") + 1);
            } else {
                result.put("F", result.get("F") + 1);
            }
        }

        return result;
    }

    List<CourseEnrollment> top(int count) {
        List<CourseEnrollment> result =
                new ArrayList<>(order);

        result.sort(
                Comparator.comparingInt(
                        CourseEnrollment::getScore)
                        .reversed()
                        .thenComparing(
                                CourseEnrollment::getStudentId));

        if (count <= 0) {
            return new ArrayList<>();
        }

        if (count < result.size()) {
            return new ArrayList<>(
                    result.subList(0, count));
        }

        return result;
    }

    void removeBelow(int minimum) {
        order.removeIf(
                enrollment ->
                        enrollment.getScore() < minimum);

        registeredIds.clear();
        byId.clear();

        for (CourseEnrollment enrollment : order) {
            registeredIds.add(
                    enrollment.getStudentId());

            byId.put(
                    enrollment.getStudentId(),
                    enrollment);
        }
    }

    List<CourseEnrollment> getAll() {
        return new ArrayList<>(order);
    }
}

public class CourseCollectionManager {
    public static void main(String[] args) {
        CourseManager manager =
                new CourseManager();

        CourseEnrollment amy =
                new CourseEnrollment(
                        "S101", "Amy", 88);

        CourseEnrollment ben =
                new CourseEnrollment(
                        "S102", "Ben", 55);

        CourseEnrollment cara =
                new CourseEnrollment(
                        "S103", "Cara", 92);

        CourseEnrollment david =
                new CourseEnrollment(
                        "S104", "David", 75);

        CourseEnrollment emily =
                new CourseEnrollment(
                        "S105", "Emily", 88);

        CourseEnrollment frank =
                new CourseEnrollment(
                        "S106", "Frank", 60);

        amy.addTag("Java");
        amy.addTag("java");

        ben.addTag("Database");

        cara.addTag("Tree");

        david.addTag("Java");

        emily.addTag("Java");
        emily.addTag(" ");

        frank.addTag("Database");

        System.out.println(
                "enroll S101="
                + manager.enroll(amy));

        System.out.println(
                "enroll duplicate S101="
                + manager.enroll(
                        new CourseEnrollment(
                                "S101",
                                "Amy2",
                                100)));

        System.out.println(
                "enroll S102="
                + manager.enroll(ben));

        System.out.println(
                "enroll S103="
                + manager.enroll(cara));

        System.out.println(
                "enroll S104="
                + manager.enroll(david));

        System.out.println(
                "enroll S105="
                + manager.enroll(emily));

        System.out.println(
                "enroll S106="
                + manager.enroll(frank));

        System.out.println(
                "all="
                + manager.getAll());

        System.out.println(
                "update S102="
                + manager.updateScore("S102", 82));

        System.out.println(
                "S102="
                + manager.findByTag("Database"));

        System.out.println(
                "Java="
                + manager.findByTag("Java"));

        System.out.println(
                "score distribution="
                + manager.scoreDistribution());

        System.out.println(
                "top 3="
                + manager.top(3));

        System.out.println(
                "top 10="
                + manager.top(10));

        manager.removeBelow(70);

        System.out.println(
                "after removeBelow 70="
                + manager.getAll());

        System.out.println(
                "after distribution="
                + manager.scoreDistribution());

        System.out.println(
                "after top 10="
                + manager.top(10));
    }
}