import java.util.*;

public class EnrollmentConflictSet {

    private final Set<String> enrollments = new LinkedHashSet<>();
    private final Map<String, Set<String>> coursesByStudent = new LinkedHashMap<>();
    private final Map<String, Set<String>> studentsByCourse = new LinkedHashMap<>();

    private String makeKey(String studentId, String courseCode) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId");
        }

        if (courseCode == null || courseCode.isBlank()) {
            throw new IllegalArgumentException("courseCode");
        }

        return studentId.trim() + "|" + courseCode.trim();
    }

    public boolean enroll(String studentId, String courseCode) {

        studentId = studentId.trim();
        courseCode = courseCode.trim();

        String key = makeKey(studentId, courseCode);

        if (enrollments.contains(key)) {
            return false;
        }

        enrollments.add(key);

        coursesByStudent
            .computeIfAbsent(studentId, k -> new LinkedHashSet<>())
            .add(courseCode);

        studentsByCourse
            .computeIfAbsent(courseCode, k -> new LinkedHashSet<>())
            .add(studentId);

        return true;
    }

    public boolean isEnrolled(String studentId, String courseCode) {
        return enrollments.contains(
            makeKey(studentId.trim(), courseCode.trim())
        );
    }

    public Set<String> coursesOfStudent(String studentId) {
        Set<String> courses = coursesByStudent.get(studentId);

        if (courses == null) {
            return Collections.emptySet();
        }

        return new LinkedHashSet<>(courses);
    }

    public int enrollmentCount(String courseCode) {
        Set<String> students = studentsByCourse.get(courseCode);

        if (students == null) {
            return 0;
        }

        return students.size();
    }

    public Set<String> studentsOfCourse(String courseCode) {
        Set<String> students = studentsByCourse.get(courseCode);

        if (students == null) {
            return Collections.emptySet();
        }

        return new LinkedHashSet<>(students);
    }

    public void printReport() {

        System.out.println("");

        for (String student : coursesByStudent.keySet()) {
            System.out.println(
                student + " -> " + coursesOfStudent(student)
            );
        }

        System.out.println();

        System.out.println("");

        for (String course : studentsByCourse.keySet()) {
            System.out.println(
                course + " -> " + enrollmentCount(course)
            );
        }
    }

    public static void main(String[] args) {

        EnrollmentConflictSet system =
            new EnrollmentConflictSet();

        System.out.println(
            "01 1: "
            + system.enroll("01", "1")
        );

        System.out.println(
            "01 2: "
            + system.enroll("01", "2")
        );

        System.out.println(
            "02 1: "
            + system.enroll("02", "1")
        );

        System.out.println(
            "03 1: "
            + system.enroll("03", "1")
        );

        System.out.println(
            "01 1 again: "
            + system.enroll("01", "1")
        );

        System.out.println();

        System.out.println(
            "01 enrolled in 1: "
            + system.isEnrolled("01", "1")
        );

        System.out.println(
            "01 enrolled in 3: "
            + system.isEnrolled("01", "3")
        );

        System.out.println();

        system.printReport();
    }
}