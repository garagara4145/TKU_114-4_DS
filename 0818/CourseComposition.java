class Instructor {
    private String id;
    private String name;

    Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }
    String label() {
        return id + " " + name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    String summary() {
        return courseCode + " " + title
                + " | Instructor: " + instructor.label();
    }
}

public class CourseComposition {

    public static void main(String[] args) {


        Instructor instructor =
                new Instructor("01", "A");

        Course course1 =
                new Course("01", "Java", instructor);

        Course course2 =
                new Course("02", "Data Structure", instructor);
        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}