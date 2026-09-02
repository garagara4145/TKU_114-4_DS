import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseGradeMap {

    private final Map<String, List<Integer>> grades = new LinkedHashMap<>();

    public void addGrade(String course, int grade) {
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("course");
        }

        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("grade");
        }

        grades.computeIfAbsent(course.trim(), key -> new ArrayList<>())
              .add(grade);
    }

    public double average(String course) {
        List<Integer> scores = grades.get(course);

        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }

        int sum = 0;

        for (int score : scores) {
            sum += score;
        }

        return (double) sum / scores.size();
    }

    public int highest(String course) {
        List<Integer> scores = grades.get(course);

        if (scores == null || scores.isEmpty()) {
            return -1;
        }

        return Collections.max(scores);
    }

    public List<String> sortedReport() {
        List<String> courses = new ArrayList<>(grades.keySet());
        Collections.sort(courses);

        List<String> report = new ArrayList<>();

        for (String course : courses) {
            report.add(
                course
                + " grades=" + grades.get(course)
                + ", average=" + String.format("%.2f", average(course))
                + ", highest=" + highest(course)
            );
        }

        return report;
    }

    public static void main(String[] args) {

        CourseGradeMap map = new CourseGradeMap();

        map.addGrade("IM101", 80);
        map.addGrade("IM101", 90);
        map.addGrade("IM101", 75);

        map.addGrade("CS101", 88);
        map.addGrade("CS101", 92);

        map.addGrade("DS101", 70);
        map.addGrade("DS101", 85);
        map.addGrade("DS101", 95);

        System.out.println("IM101 average="
                + String.format("%.2f", map.average("IM101")));

        System.out.println("IM101 highest="
                + map.highest("IM101"));

        System.out.println("--- sorted report ---");

        for (String line : map.sortedReport()) {
            System.out.println(line);
        }
    }
}