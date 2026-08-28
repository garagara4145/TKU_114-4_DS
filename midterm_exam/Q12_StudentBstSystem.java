import java.util.ArrayList;
import java.util.List;

public class Q12_StudentBstSystem {

    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0) {
                throw new IllegalArgumentException();
            }

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id;
            this.name = name.trim();
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        private void setScore(int score) {
            this.score = Math.max(0, Math.min(100, score));
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        Node current = root;

        while (true) {
            if (student.getId() == current.student.getId()) {
                return false;
            }

            if (student.getId() < current.student.getId()) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }

                current = current.right;
            }
        }
    }

    public Student find(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.student.getId()) {
                return current.student;
            }

            if (id < current.student.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean updateScore(int id, int score) {
        Student student = find(id);

        if (student == null) {
            return false;
        }

        student.setScore(score);
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }

        root = removeNode(root, id);
        return true;
    }

    private Node removeNode(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.student.getId()) {
            node.left = removeNode(node.left, id);
            return node;
        }

        if (id > node.student.getId()) {
            node.right = removeNode(node.right, id);
            return node;
        }

        if (node.left == null && node.right == null) {
            return null;
        }

        if (node.left == null) {
            return node.right;
        }

        if (node.right == null) {
            return node.left;
        }

        Node successor = findMinimum(node.right);
        node.student = successor.student;
        node.right = removeNode(
                node.right, successor.student.getId());

        return node;
    }

    private Node findMinimum(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        List<Student> result = new ArrayList<>();

        if (lowId > highId) {
            return result;
        }

        studentsBetween(root, lowId, highId, result);
        return result;
    }

    private void studentsBetween(
            Node node,
            int lowId,
            int highId,
            List<Student> result) {

        if (node == null) {
            return;
        }

        int id = node.student.getId();

        if (id > lowId) {
            studentsBetween(node.left, lowId, highId, result);
        }

        if (id >= lowId && id <= highId) {
            result.add(node.student);
        }

        if (id < highId) {
            studentsBetween(node.right, lowId, highId, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Student> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.student);
        inorder(node.right, result);
    }

    public static void main(String[] args) {
        Q12_StudentBstSystem system =
                new Q12_StudentBstSystem();

        system.add(
                new Q12_StudentBstSystem.Student(
                        300, "Mina", 78));

        system.add(
                new Q12_StudentBstSystem.Student(
                        100, "Leo", 84));

        system.add(
                new Q12_StudentBstSystem.Student(
                        500, "Nora", 105));

        system.add(
                new Q12_StudentBstSystem.Student(
                        200, "Ivy", 69));

        System.out.println(system.updateScore(200, 88));
        System.out.println(system.studentsBetween(150, 500));
        System.out.println(system.remove(300));
        System.out.println(system.inorder());
    }
}