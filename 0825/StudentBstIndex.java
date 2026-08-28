class Student {
    int studentId;
    String name;
    int score;

    Student(int studentId, String name, int score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return studentId + " " + name + " score=" + score;
    }
}

class StudentNode {
    Student data;
    StudentNode left;
    StudentNode right;

    StudentNode(Student data) {
        this.data = data;
    }
}

class StudentBst {
    private StudentNode root;

    boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new StudentNode(student);
            return true;
        }

        StudentNode current = root;

        while (true) {
            if (student.studentId == current.data.studentId) {
                return false;
            }

            if (student.studentId < current.data.studentId) {
                if (current.left == null) {
                    current.left = new StudentNode(student);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new StudentNode(student);
                    return true;
                }

                current = current.right;
            }
        }
    }

    Student find(int studentId) {
        StudentNode current = root;

        while (current != null) {
            if (studentId == current.data.studentId) {
                return current.data;
            }

            if (studentId < current.data.studentId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean remove(int studentId) {
        if (find(studentId) == null) {
            return false;
        }

        root = remove(root, studentId);
        return true;
    }

    private StudentNode remove(
            StudentNode node,
            int studentId) {

        if (node == null) {
            return null;
        }

        if (studentId < node.data.studentId) {
            node.left = remove(node.left, studentId);
        } else if (studentId > node.data.studentId) {
            node.right = remove(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            StudentNode successor =
                    minimumNode(node.right);

            node.data = successor.data;

            node.right =
                    remove(
                            node.right,
                            successor.data.studentId);
        }

        return node;
    }

    private StudentNode minimumNode(StudentNode node) {
        StudentNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(StudentNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class StudentBstIndex {
    public static void main(String[] args) {

        StudentBst tree = new StudentBst();

        System.out.println(
                tree.add(
                        new Student(
                                300,
                                "Mina",
                                78)));

        System.out.println(
                tree.add(
                        new Student(
                                100,
                                "Leo",
                                84)));

        System.out.println(
                tree.add(
                        new Student(
                                500,
                                "Nora",
                                91)));

        System.out.println(
                tree.add(
                        new Student(
                                200,
                                "Ivy",
                                69)));

        System.out.println(
                tree.add(
                        new Student(
                                100,
                                "Duplicate",
                                60)));

        System.out.println("inorder:");
        tree.inorder();

        System.out.println(
                "find 200=" + tree.find(200));

        System.out.println(
                "find 999=" + tree.find(999));

        System.out.println(
                "remove 200=" + tree.remove(200));

        System.out.println("after remove:");
        tree.inorder();

        System.out.println(
                "remove 999=" + tree.remove(999));
    }
}