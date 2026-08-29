import java.util.ArrayList;
import java.util.List;

public class CourseBstIndex {

    static class Course {
        final String courseCode;
        final String courseName;
        int credit;

        Course(String courseCode, String courseName, int credit) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credit = credit;
        }

        @Override
        public String toString() {
            return courseCode + " " + courseName
                    + " credit=" + credit;
        }
    }

    static class Node {
        Course data;
        Node left;
        Node right;

        Node(Course data) {
            this.data = data;
        }
    }

    static class CourseBst {
        private Node root;

        boolean add(Course course) {
            if (course == null) {
                return false;
            }

            if (course.courseCode == null
                    || course.courseCode.isBlank()) {
                return false;
            }

            if (course.credit < 1 || course.credit > 6) {
                return false;
            }

            if (root == null) {
                root = new Node(course);
                return true;
            }

            Node current = root;

            while (true) {
                int compare = course.courseCode
                        .compareTo(current.data.courseCode);

                if (compare == 0) {
                    return false;
                }

                if (compare < 0) {
                    if (current.left == null) {
                        current.left = new Node(course);
                        return true;
                    }

                    current = current.left;

                } else {
                    if (current.right == null) {
                        current.right = new Node(course);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Course find(String courseCode) {
            if (courseCode == null) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int compare = courseCode
                        .compareTo(current.data.courseCode);

                if (compare == 0) {
                    return current.data;
                }

                if (compare < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean updateCredit(String courseCode, int credit) {
            if (credit < 1 || credit > 6) {
                return false;
            }

            Course course = find(courseCode);

            if (course == null) {
                return false;
            }

            course.credit = credit;
            return true;
        }

        boolean remove(String courseCode) {
            if (find(courseCode) == null) {
                return false;
            }

            root = remove(root, courseCode);
            return true;
        }

        private Node remove(Node node, String courseCode) {
            int compare = courseCode
                    .compareTo(node.data.courseCode);

            if (compare < 0) {
                node.left = remove(node.left, courseCode);

            } else if (compare > 0) {
                node.right = remove(node.right, courseCode);

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = minimum(node.right);

                node.data = successor.data;

                node.right = remove(
                        node.right,
                        successor.data.courseCode
                );
            }

            return node;
        }

        private Node minimum(Node node) {
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        List<Course> codeRange(
                String low,
                String high) {

            List<Course> result =
                    new ArrayList<>();

            if (low == null || high == null) {
                return result;
            }

            if (low.compareTo(high) > 0) {
                return result;
            }

            codeRange(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        private void codeRange(
                Node node,
                String low,
                String high,
                List<Course> result) {

            if (node == null) {
                return;
            }

            if (low.compareTo(
                    node.data.courseCode) < 0) {

                codeRange(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low.compareTo(
                    node.data.courseCode) <= 0
                    && node.data.courseCode
                    .compareTo(high) <= 0) {

                result.add(node.data);
            }

            if (node.data.courseCode
                    .compareTo(high) < 0) {

                codeRange(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        List<Course> inorder() {
            List<Course> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Course> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.data);

            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        CourseBst index =
                new CourseBst();

        System.out.println(
                "add=" + index.add(
                        new Course(
                                "CS101",
                                "Programming",
                                3
                        )
                )
        );

        System.out.println(
                "add=" + index.add(
                        new Course(
                                "CS201",
                                "Data Structures",
                                3
                        )
                )
        );

        System.out.println(
                "add=" + index.add(
                        new Course(
                                "CS301",
                                "Database",
                                3
                        )
                )
        );

        System.out.println(
                "add=" + index.add(
                        new Course(
                                "CS150",
                                "Computer Organization",
                                2
                        )
                )
        );

        System.out.println(
                "add=" + index.add(
                        new Course(
                                "CS250",
                                "Operating Systems",
                                4
                        )
                )
        );

        System.out.println(
                "duplicate=" + index.add(
                        new Course(
                                "CS101",
                                "Other Course",
                                2
                        )
                )
        );

        System.out.println(
                "invalidCredit=" + index.add(
                        new Course(
                                "CS400",
                                "Invalid Course",
                                7
                        )
                )
        );

        System.out.println(
                "find=" + index.find("CS250")
        );

        System.out.println(
                "missing=" + index.find("CS999")
        );

        System.out.println(
                "updateCredit=" +
                index.updateCredit(
                        "CS250",
                        3
                )
        );

        System.out.println(
                "invalidUpdate=" +
                index.updateCredit(
                        "CS250",
                        8
                )
        );

        System.out.println(
                "remove=" +
                index.remove("CS150")
        );

        System.out.println(
                "missingRemove=" +
                index.remove("CS999")
        );

        System.out.println(
                "--- Range [CS150, CS300] ---"
        );

        for (Course course :
                index.codeRange(
                        "CS150",
                        "CS300")) {

            System.out.println(course);
        }

        System.out.println();

        System.out.println(
                "--- Inorder Report ---"
        );

        for (Course course :
                index.inorder()) {

            System.out.println(course);
        }
    }
}