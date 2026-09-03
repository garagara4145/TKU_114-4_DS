import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Q05_StudentHashIndex {

    private HashMap<String, Set<String>> studentCourses;
    private HashMap<String, Set<String>> courseStudents;
    private int count;

    public Q05_StudentHashIndex() {
        studentCourses = new HashMap<>();
        courseStudents = new HashMap<>();
        count = 0;
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }

        value = value.trim();

        if (value.isEmpty()) {
            return null;
        }

        return value.toUpperCase();
    }

    public boolean enroll(String studentId, String courseId) {
        studentId = normalize(studentId);
        courseId = normalize(courseId);

        if (studentId == null || courseId == null) {
            return false;
        }

        Set<String> courses = studentCourses.get(studentId);

        if (courses == null) {
            courses = new HashSet<>();
            studentCourses.put(studentId, courses);
        }

        if (courses.contains(courseId)) {
            return false;
        }

        courses.add(courseId);

        Set<String> students = courseStudents.get(courseId);

        if (students == null) {
            students = new HashSet<>();
            courseStudents.put(courseId, students);
        }

        students.add(studentId);

        count++;

        return true;
    }

    public boolean drop(String studentId, String courseId) {
        studentId = normalize(studentId);
        courseId = normalize(courseId);

        if (studentId == null || courseId == null) {
            return false;
        }

        Set<String> courses = studentCourses.get(studentId);

        if (courses == null || !courses.contains(courseId)) {
            return false;
        }

        courses.remove(courseId);

        if (courses.isEmpty()) {
            studentCourses.remove(studentId);
        }

        Set<String> students = courseStudents.get(courseId);

        if (students != null) {
            students.remove(studentId);

            if (students.isEmpty()) {
                courseStudents.remove(courseId);
            }
        }

        count--;

        return true;
    }

    public Set<String> coursesOf(String studentId) {
        studentId = normalize(studentId);

        if (studentId == null) {
            return Set.of();
        }

        Set<String> courses = studentCourses.get(studentId);

        if (courses == null) {
            return Set.of();
        }

        return Set.copyOf(courses);
    }

    public Set<String> studentsIn(String courseId) {
        courseId = normalize(courseId);

        if (courseId == null) {
            return Set.of();
        }

        Set<String> students = courseStudents.get(courseId);

        if (students == null) {
            return Set.of();
        }

        return Set.copyOf(students);
    }

    public int enrollmentCount() {
        return count;
    }

    public static void main(String[] args) {
        Q05_StudentHashIndex index =
                new Q05_StudentHashIndex();

        System.out.println("選課A"
                + index.enroll(" a001 ", " ds101 "));

        System.out.println("選課A"
                + index.enroll("A001", "DS102"));

        System.out.println("選課B "
                + index.enroll("B001", "DS101"));

        System.out.println("重複選課"
                + index.enroll("A001", "DS101"));

        System.out.println("A001課程"
                + index.coursesOf(" a001 "));

        System.out.println("DS101的學生"
                + index.studentsIn(" ds101 "));

        System.out.println("選課數"
                + index.enrollmentCount());

        System.out.println("退選A001 DS101 = "
                + index.drop(" a001 ", " ds101 "));

        System.out.println("A001課程"
                + index.coursesOf("A001"));

        System.out.println("DS101學生"
                + index.studentsIn("DS101"));

        System.out.println("退選後數量"
                + index.enrollmentCount());

        System.out.println("無"
                + index.enroll("   ", "DS103"));

        System.out.println("滿堂"
                + index.enroll("C001", null));
    }
}