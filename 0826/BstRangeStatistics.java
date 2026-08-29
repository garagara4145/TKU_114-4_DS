import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

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

        List<Integer> valuesBetween(int low, int high) {
            List<Integer> result = new ArrayList<>();

            if (low > high) {
                return result;
            }

            valuesBetween(root, low, high, result);

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
                valuesBetween(node.left, low, high, result);
            }

            if (low <= node.value && node.value <= high) {
                result.add(node.value);
            }

            if (node.value < high) {
                valuesBetween(node.right, low, high, result);
            }
        }

        int countBetween(int low, int high) {
            if (low > high) {
                return 0;
            }

            return countBetween(root, low, high);
        }

        private int countBetween(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return 0;
            }

            int count = 0;

            if (low < node.value) {
                count += countBetween(node.left, low, high);
            }

            if (low <= node.value && node.value <= high) {
                count++;
            }

            if (node.value < high) {
                count += countBetween(node.right, low, high);
            }

            return count;
        }

        int sumBetween(int low, int high) {
            if (low > high) {
                return 0;
            }

            return sumBetween(root, low, high);
        }

        private int sumBetween(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return 0;
            }

            int sum = 0;

            if (low < node.value) {
                sum += sumBetween(node.left, low, high);
            }

            if (low <= node.value && node.value <= high) {
                sum += node.value;
            }

            if (node.value < high) {
                sum += sumBetween(node.right, low, high);
            }

            return sum;
        }

        List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();

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
    }

    public static void main(String[] args) {

        Bst tree = new Bst();

        int[] data = {
            50, 30, 70, 20, 40, 60, 80
        };

        for (int value : data) {
            tree.add(value);
        }

        System.out.println("--- BST ---");
        System.out.println("inorder=" + tree.inorder());

        System.out.println();

        System.out.println("--- Range [35, 70] ---");

        System.out.println(
            "values=" + tree.valuesBetween(35, 70)
        );

        System.out.println(
            "count=" + tree.countBetween(35, 70)
        );

        System.out.println(
            "sum=" + tree.sumBetween(35, 70)
        );

        System.out.println();

        System.out.println("--- Range [30, 70] ---");

        System.out.println(
            "values=" + tree.valuesBetween(30, 70)
        );

        System.out.println(
            "count=" + tree.countBetween(30, 70)
        );

        System.out.println(
            "sum=" + tree.sumBetween(30, 70)
        );

        System.out.println();

        System.out.println("--- Empty Range [90, 100] ---");

        System.out.println(
            "values=" + tree.valuesBetween(90, 100)
        );

        System.out.println(
            "count=" + tree.countBetween(90, 100)
        );

        System.out.println(
            "sum=" + tree.sumBetween(90, 100)
        );

        System.out.println();

        System.out.println("--- Invalid Range [80, 30] ---");

        System.out.println(
            "values=" + tree.valuesBetween(80, 30)
        );

        System.out.println(
            "count=" + tree.countBetween(80, 30)
        );

        System.out.println(
            "sum=" + tree.sumBetween(80, 30)
        );
    }
}