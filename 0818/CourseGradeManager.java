class CourseGrade {
    private String studentId;
    private String name;
    private int daily;
    private int midterm;
    private int finalExam;
    private int attendance;

    CourseGrade(String studentId, String name,
                int daily, int midterm,
                int finalExam, int attendance) {

        this.studentId = studentId;
        this.name = name;

        this.daily = limitScore(daily);
        this.midterm = limitScore(midterm);
        this.finalExam = limitScore(finalExam);
        this.attendance = limitScore(attendance);
    }

    private int limitScore(int score) {
        if (score < 0) {
            return 0;
        }

        if (score > 100) {
            return 100;
        }

        return score;
    }

    double calculateFinalScore() {
        return daily * 0.50
                + midterm * 0.20
                + finalExam * 0.20
                + attendance * 0.10;
    }

    String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    double getFinalScore() {
        return calculateFinalScore();
    }

    boolean isFailed() {
        return calculateFinalScore() < 60;
    }

    @Override
    public String toString() {
        return studentId + " "
                + name
                + " final="
                + String.format("%.1f", calculateFinalScore())
                + " level="
                + getLevel();
    }
}

public class CourseGradeManager {

    public static void main(String[] args) {

        CourseGrade[] students = {
            new CourseGrade(
                    "S001", "Amy",
                    90, 85, 88, 95),

            new CourseGrade(
                    "S002", "Ben",
                    70, 75, 68, 80),

            new CourseGrade(
                    "S003", "Cara",
                    95, 92, 96, 100),

            new CourseGrade(
                    "S004", "David",
                    50, 45, 55, 60),

            new CourseGrade(
                    "S005", "Eva",
                    60, 58, 62, 70)
        };
        System.out.println("所有學生成績：");

        for (CourseGrade student : students) {
            System.out.println(student);
        }
        double total = 0;

        for (CourseGrade student : students) {
            total += student.getFinalScore();
        }

        double average = total / students.length;

        System.out.printf(
                "\n平均分：%.1f%n",
                average);

        CourseGrade highest = students[0];

        for (CourseGrade student : students) {
            if (student.getFinalScore()
                    > highest.getFinalScore()) {

                highest = student;
            }
        }

        System.out.println("\n最高分：");
        System.out.println(highest);

        System.out.println("\n不及格名單：");

        for (CourseGrade student : students) {
            if (student.isFailed()) {
                System.out.println(student);
            }
        }
    }
}