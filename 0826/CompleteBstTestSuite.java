import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

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

        boolean remove(int value) {

            if (!contains(value)) {
                return false;
            }

            root = remove(root, value);

            return true;
        }

        private Node remove(Node node, int value) {

            if (value < node.value) {

                node.left = remove(
                        node.left,
                        value
                );

            } else if (value > node.value) {

                node.right = remove(
                        node.right,
                        value
                );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = minimum(
                        node.right
                );

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

        List<Integer> inorder() {

            List<Integer> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.value);

            inorder(node.right, result);
        }

        int size() {
            return size(root);
        }

        private int size(Node node) {

            if (node == null) {
                return 0;
            }

            return 1
                    + size(node.left)
                    + size(node.right);
        }

        int height() {
            return height(root);
        }

        private int height(Node node) {

            if (node == null) {
                return -1;
            }

            return 1 + Math.max(
                    height(node.left),
                    height(node.right)
            );
        }

        int leafCount() {
            return leafCount(root);
        }

        private int leafCount(Node node) {

            if (node == null) {
                return 0;
            }

            if (node.left == null &&
                    node.right == null) {
                return 1;
            }

            return leafCount(node.left)
                    + leafCount(node.right);
        }

        List<Integer> valuesBetween(
                int low,
                int high) {

            List<Integer> result =
                    new ArrayList<>();

            if (low > high) {
                return result;
            }

            valuesBetween(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        private void valuesBetween(
                Node node,
                int low,
                int high,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            if (low < node.value) {
                valuesBetween(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low <= node.value &&
                    node.value <= high) {

                result.add(node.value);
            }

            if (node.value < high) {
                valuesBetween(
                        node.right,
                        low,
                        high,
                        result
                );
            }
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

    static int pass = 0;
    static int fail = 0;

    static void check(
            String description,
            boolean condition) {

        if (condition) {
            System.out.println(
                    "PASS: " + description
            );
            pass++;
        } else {
            System.out.println(
                    "FAIL: " + description
            );
            fail++;
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "=== Complete BST Test Suite ==="
        );

        Bst tree = new Bst();

        // 1
        check(
                "empty contains is false",
                !tree.contains(10)
        );

        // 2
        check(
                "empty remove is false",
                !tree.remove(10)
        );

        // 3
        check(
                "empty size is 0",
                tree.size() == 0
        );

        // 4
        check(
                "empty height is -1",
                tree.height() == -1
        );

        // 5
        check(
                "empty leaf count is 0",
                tree.leafCount() == 0
        );

        // 6
        check(
                "empty tree is valid",
                tree.isValid()
        );

        // 7
        check(
                "add root",
                tree.add(50)
        );

        // 8
        check(
                "root contains 50",
                tree.contains(50)
        );

        // 9
        check(
                "size after root",
                tree.size() == 1
        );

        // 10
        check(
                "height after root",
                tree.height() == 0
        );

        // 11
        check(
                "leaf count after root",
                tree.leafCount() == 1
        );

        // 12
        check(
                "duplicate is rejected",
                !tree.add(50)
        );

        // 13
        check(
                "duplicate does not change size",
                tree.size() == 1
        );

        // Add more nodes
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        // 14
        check(
                "inorder is sorted",
                tree.inorder().equals(
                        List.of(
                                20, 30, 40,
                                50, 60, 70, 80
                        )
                )
        );

        // 15
        check(
                "size is 7",
                tree.size() == 7
        );

        // 16
        check(
                "height is 2",
                tree.height() == 2
        );

        // 17
        check(
                "leaf count is 4",
                tree.leafCount() == 4
        );

        // 18
        check(
                "tree is valid",
                tree.isValid()
        );

        // 19
        check(
                "find existing 20",
                tree.contains(20)
        );

        // 20
        check(
                "find existing 80",
                tree.contains(80)
        );

        // 21
        check(
                "missing value 999",
                !tree.contains(999)
        );

        // 22
        check(
                "remove missing 999",
                !tree.remove(999)
        );

        // 23
        check(
                "remove leaf 20",
                tree.remove(20)
        );

        // 24
        check(
                "leaf removed",
                !tree.contains(20)
        );

        // 25
        check(
                "size after leaf removal",
                tree.size() == 6
        );

        // 26
        check(
                "remove one-child node 30",
                tree.remove(30)
        );

        // 27
        check(
                "child 40 is preserved",
                tree.contains(40)
        );

        // 28
        check(
                "size after one-child removal",
                tree.size() == 5
        );

        // 29
        check(
                "remove two-child node 70",
                tree.remove(70)
        );

        // 30
        check(
                "60 is preserved",
                tree.contains(60)
        );

        // 31
        check(
                "80 is preserved",
                tree.contains(80)
        );

        // 32
        check(
                "size after two-child removal",
                tree.size() == 4
        );

        // 33
        check(
                "inorder after removals",
                tree.inorder().equals(
                        List.of(
                                40, 50, 60, 80
                        )
                )
        );

        // 34
        check(
                "tree valid after removals",
                tree.isValid()
        );

        // 35
        check(
                "range [40, 60]",
                tree.valuesBetween(40, 60)
                        .equals(
                                List.of(40, 50, 60)
                        )
        );

        // 36
        check(
                "range includes low boundary",
                tree.valuesBetween(40, 40)
                        .equals(
                                List.of(40)
                        )
        );

        // 37
        check(
                "range includes high boundary",
                tree.valuesBetween(80, 80)
                        .equals(
                                List.of(80)
                        )
        );

        // 38
        check(
                "empty range has no values",
                tree.valuesBetween(90, 100)
                        .isEmpty()
        );

        // 39
        check(
                "low greater than high",
                tree.valuesBetween(80, 40)
                        .isEmpty()
        );

        // 40
        check(
                "final invariant is valid",
                tree.isValid()
        );

        System.out.println();
        System.out.println(
                "=== Test Summary ==="
        );

        System.out.println(
                "PASS=" + pass
        );

        System.out.println(
                "FAIL=" + fail
        );

        System.out.println(
                "TOTAL=" + (pass + fail)
        );
    }
}