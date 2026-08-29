import java.util.ArrayList;
import java.util.List;

public class TreeBugLab {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Bst {
        private Node root;

        boolean add(int value) {
            if (root == null) {
                root = new Node(value);
                return true;
            }

            Node current = root;

            while (true) {
                if (value == current.value) {
                    return false;
                }

                if (value < current.value) {
                    if (current.left == null) {
                        current.left = new Node(value);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(value);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        boolean contains(int value) {
            Node current = root;

            while (current != null) {
                if (value == current.value) {
                    return true;
                }

                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }

        List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(Node node, List<Integer> result) {
            if (node == null) {
                return;
            }

            inorder(node.left, result);
            result.add(node.value);
            inorder(node.right, result);
        }

        boolean remove(int value) {
            if (!contains(value)) {
                return false;
            }

            root = remove(root, value);
            return true;
        }

        private Node remove(Node node, int value) {
            if (value < node.value) {
                node.left = remove(node.left, value);
            } else if (value > node.value) {
                node.right = remove(node.right, value);
            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = minimum(node.right);
                node.value = successor.value;
                node.right = remove(
                        node.right,
                        successor.value
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

        boolean isValid() {
            return isValid(
                    root,
                    Long.MIN_VALUE,
                    Long.MAX_VALUE
            );
        }

        private boolean isValid(
                Node node,
                long low,
                long high) {

            if (node == null) {
                return true;
            }

            if (node.value <= low ||
                node.value >= high) {
                return false;
            }

            return isValid(
                    node.left,
                    low,
                    node.value
            ) && isValid(
                    node.right,
                    node.value,
                    high
            );
        }
    }

    static void test(
            String name,
            boolean condition) {

        if (condition) {
            System.out.println(
                    "[PASS] " + name
            );
        } else {
            System.out.println(
                    "[FAIL] " + name
            );
        }
    }

    static void testSearchDirection() {

        System.out.println(
                "--- Bug 1: Search Direction ---"
        );

        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(70);

        test(
                "find existing 30",
                tree.contains(30)
        );

        test(
                "find existing 70",
                tree.contains(70)
        );

        test(
                "missing 90",
                !tree.contains(90)
        );

        System.out.println();
    }

    static void testInorder() {

        System.out.println(
                "--- Bug 2: Inorder Order ---"
        );

        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        List<Integer> expected =
                List.of(
                        20, 30, 40,
                        50, 60, 70, 80
                );

        test(
                "inorder is ascending",
                tree.inorder().equals(expected)
        );

        System.out.println(
                "inorder=" + tree.inorder()
        );

        System.out.println();
    }

    static void testDeleteOneChild() {

        System.out.println(
                "--- Bug 3: Delete One Child ---"
        );

        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(20);

        test(
                "before remove",
                tree.inorder().equals(
                        List.of(20, 30, 50)
                )
        );

        test(
                "remove 30",
                tree.remove(30)
        );

        test(
                "child 20 is preserved",
                tree.inorder().equals(
                        List.of(20, 50)
                )
        );

        System.out.println(
                "inorder=" + tree.inorder()
        );

        System.out.println();
    }

    static void testValidation() {

        System.out.println(
                "--- Bug 4: Deep Validation ---"
        );

        Bst tree = new Bst();

        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);

        test(
                "normal tree is valid",
                tree.isValid()
        );

        System.out.println();
    }

    static void demonstrateDeepViolation() {

        System.out.println(
                "--- Deep Violation Example ---"
        );

        Node root = new Node(50);

        root.left = new Node(30);

        root.left.right = new Node(60);

        System.out.println(
                "root=50"
        );

        System.out.println(
                "left=30"
        );

        System.out.println(
                "left.right=60"
        );

        System.out.println(
                "60 > 30, but 60 must be < 50"
        );

        System.out.println(
                "A parent-child-only check would miss this."
        );

        System.out.println();
    }

    public static void main(String[] args) {

        testSearchDirection();

        testInorder();

        testDeleteOneChild();

        testValidation();

        demonstrateDeepViolation();
    }
}