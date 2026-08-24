import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private final String studentId;
    private final String courseCode;

    Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Enrollment enrollment)) {
            return false;
        }

        return Objects.equals(studentId, enrollment.studentId)
                && Objects.equals(courseCode, enrollment.courseCode);
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

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        Enrollment e1 =
                new Enrollment("S101", "JAVA");

        Enrollment e2 =
                new Enrollment("S101", "DATABASE");

        Enrollment e3 =
                new Enrollment("S101", "JAVA");

        Enrollment e4 =
                new Enrollment("S102", "JAVA");

        System.out.println(
                "新增 S101 JAVA="
                + enrollments.add(e1));

        System.out.println(
                "新增 S101 DATABASE="
                + enrollments.add(e2));

        System.out.println(
                "新增重複 S101 JAVA="
                + enrollments.add(e3));

        System.out.println(
                "新增 S102 JAVA="
                + enrollments.add(e4));

        Enrollment search =
                new Enrollment("S101", "JAVA");

        System.out.println(
                "contains="
                + enrollments.contains(search));

        System.out.println(
                "remove="
                + enrollments.remove(search));

        System.out.println(
                "remove 後 contains="
                + enrollments.contains(search));

        System.out.println(
                "目前報名資料="
                + enrollments);
    }
}