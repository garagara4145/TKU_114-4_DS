import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> enrollmentMapR26;

    public Q06_EnrollmentIndex() {
        enrollmentMapR26 = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty()
                || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = enrollmentMapR26.computeIfAbsent(
                courseCode, key -> new HashSet<>());

        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty()
                || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = enrollmentMapR26.get(courseCode);

        if (students == null) {
            return false;
        }

        boolean removed = students.remove(studentId);

        if (students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }

        return removed;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return 0;
        }

        Set<String> students = enrollmentMapR26.get(courseCode.trim());

        if (students == null) {
            return 0;
        }

        return students.size();
    }

    public List<String> studentsOf(String courseCode) {
        List<String> result = new ArrayList<>();

        if (courseCode == null || courseCode.trim().isEmpty()) {
            return result;
        }

        Set<String> students = enrollmentMapR26.get(courseCode.trim());

        if (students == null) {
            return result;
        }

        result.addAll(students);
        result.sort(String::compareTo);

        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();

        if (studentId == null || studentId.trim().isEmpty()) {
            return result;
        }

        studentId = studentId.trim();

        for (Map.Entry<String, Set<String>> entry
                : enrollmentMapR26.entrySet()) {

            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }

        result.sort(String::compareTo);

        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new TreeMap<>();

        for (Map.Entry<String, Set<String>> entry
                : enrollmentMapR26.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }

        return result;
    }

    public static void main(String[] args) {
        Q06_EnrollmentIndex index = new Q06_EnrollmentIndex();

        index.enroll("DS", "S02");
        index.enroll("DS", "S01");
        index.enroll("JAVA", "S01");

        System.out.println(index.studentsOf("DS"));
        System.out.println(index.coursesOf("S01"));
        System.out.println(index.summary());
    }
}