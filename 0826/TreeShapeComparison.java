import java.util.ArrayList;
import java.util.List;

public class TreeShapeComparison {

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

        int searchComparisons(int value) {

            Node current = root;
            int comparisons = 0;

            while (current != null) {

                comparisons++;

                if (value == current.value) {
                    return comparisons;
                }

                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return comparisons;
        }

        int totalSearchComparisons() {

            int total = 0;

            for (int value : inorder()) {
                total += searchComparisons(value);
            }

            return total;
        }

        int missingSearchComparisons(int value) {
            return searchComparisons(value);
        }
    }

    static Bst buildTree(int[] values) {

        Bst tree = new Bst();

        for (int value : values) {
            tree.add(value);
        }

        return tree;
    }

    static void printReport(
            String name,
            Bst tree,
            int missingValue) {

        System.out.println(
                "--- " + name + " ---"
        );

        System.out.println(
                "inorder=" + tree.inorder()
        );

        System.out.println(
                "height=" + tree.height()
        );

        System.out.println(
                "total search comparisons="
                + tree.totalSearchComparisons()
        );

        System.out.println(
                "missing key="
                + missingValue
                + ", comparisons="
                + tree.missingSearchComparisons(
                        missingValue
                )
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] ascending = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] descending = {
            15, 14, 13, 12, 11,
            10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };

        int[] balanced = {
            8,
            4, 12,
            2, 6, 10, 14,
            1, 3, 5, 7,
            9, 11, 13, 15
        };

        Bst ascendingTree =
                buildTree(ascending);

        Bst descendingTree =
                buildTree(descending);

        Bst balancedTree =
                buildTree(balanced);

        printReport(
                "Ascending",
                ascendingTree,
                100
        );

        printReport(
                "Descending",
                descendingTree,
                100
        );

        printReport(
                "Balanced",
                balancedTree,
                100
        );
    }
}