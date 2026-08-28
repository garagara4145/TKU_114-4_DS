import java.util.ArrayList;
import java.util.List;

class ScoreStudent {
    private final int studentId;
    private final String name;
    private final int score;

    ScoreStudent(int studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
    }

    int getStudentId() {
        return studentId;
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return studentId + "|" + name + "|" + score;
    }
}

class ScoreNode {
    ScoreStudent data;
    ScoreNode left;
    ScoreNode right;

    ScoreNode(ScoreStudent data) {
        this.data = data;
    }
}

class ScoreBst {
    private ScoreNode root;

    boolean add(ScoreStudent student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new ScoreNode(student);
            return true;
        }

        ScoreNode current = root;

        while (true) {
            int compare = compare(student, current.data);

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {
                if (current.left == null) {
                    current.left = new ScoreNode(student);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ScoreNode(student);
                    return true;
                }

                current = current.right;
            }
        }
    }

    private int compare(
            ScoreStudent a,
            ScoreStudent b) {

        if (a.getScore() != b.getScore()) {
            return Integer.compare(
                    a.getScore(),
                    b.getScore());
        }

        return Integer.compare(
                a.getStudentId(),
                b.getStudentId());
    }

    List<ScoreStudent> range(
            int lowScore,
            int highScore) {

        List<ScoreStudent> result =
                new ArrayList<>();

        if (lowScore > highScore) {
            return result;
        }

        range(
                root,
                lowScore,
                highScore,
                result);

        return result;
    }

    private void range(
            ScoreNode node,
            int lowScore,
            int highScore,
            List<ScoreStudent> result) {

        if (node == null) {
            return;
        }

        int score = node.data.getScore();

        if (score > lowScore) {
            range(
                    node.left,
                    lowScore,
                    highScore,
                    result);
        }

        if (score >= lowScore
                && score <= highScore) {
            result.add(node.data);
        }

        if (score < highScore) {
            range(
                    node.right,
                    lowScore,
                    highScore,
                    result);
        }
    }

    List<ScoreStudent> inorder() {
        List<ScoreStudent> result =
                new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private void inorder(
            ScoreNode node,
            List<ScoreStudent> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }
}

public class ScoreRangeBst {

    public static void main(String[] args) {

        ScoreBst tree = new ScoreBst();

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                100,
                                "Amy",
                                80)));

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                101,
                                "Bob",
                                80)));

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                102,
                                "Cindy",
                                90)));

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                103,
                                "David",
                                70)));

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                104,
                                "Eva",
                                90)));

        System.out.println(
                tree.add(
                        new ScoreStudent(
                                100,
                                "Duplicate",
                                95)));

        System.out.println("=== inorder ===");

        for (ScoreStudent student : tree.inorder()) {
            System.out.println(student);
        }

        System.out.println("=== range 80..90 ===");

        for (ScoreStudent student :
                tree.range(80, 90)) {
            System.out.println(student);
        }

        System.out.println("=== range 70..80 ===");

        for (ScoreStudent student :
                tree.range(70, 80)) {
            System.out.println(student);
        }

        System.out.println("=== range 95..100 ===");

        for (ScoreStudent student :
                tree.range(95, 100)) {
            System.out.println(student);
        }

        System.out.println("=== invalid range ===");

        System.out.println(
                tree.range(90, 80));

        System.out.println("=== empty range ===");

        System.out.println(
                tree.range(200, 300));
    }
}