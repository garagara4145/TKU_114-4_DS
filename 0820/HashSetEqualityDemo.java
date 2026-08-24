import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class EnrollmentKey {
    private final String studentId;
    private final String courseCode;

    EnrollmentKey(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EnrollmentKey key)) {
            return false;
        }

        return Objects.equals(studentId, key.studentId)
                && Objects.equals(courseCode, key.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + " " + courseCode;
    }
}

public class HashSetEqualityDemo {
    public static void main(String[] args) {
        Set<EnrollmentKey> enrollments = new HashSet<>();

        System.out.println(
                enrollments.add(
                        new EnrollmentKey("S101", "Java")));

        System.out.println(
                enrollments.add(
                        new EnrollmentKey("S101", "Database")));

        System.out.println(
                enrollments.add(
                        new EnrollmentKey("S101", "Java")));

        System.out.println(
                enrollments.add(
                        new EnrollmentKey("S102", "Java")));

        System.out.println(
                "size=" + enrollments.size());

        System.out.println(enrollments);
    }
}