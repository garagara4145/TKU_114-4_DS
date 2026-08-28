class ShapeNode {
    int value;
    ShapeNode left;
    ShapeNode right;

    ShapeNode(int value) {
        this.value = value;
    }
}

class ShapeBst {
    private ShapeNode root;

    boolean add(int value) {
        if (root == null) {
            root = new ShapeNode(value);
            return true;
        }

        ShapeNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeNode(value);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeNode(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    int size() {
        return size(root);
    }

    private int size(ShapeNode node) {
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

    private int height(ShapeNode node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    int searchComparisons(int target) {
        ShapeNode current = root;
        int comparisons = 0;

        while (current != null) {
            comparisons++;

            if (target == current.value) {
                return comparisons;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return comparisons;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(ShapeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class BstShapeExperiment {

    private static final int[] VALUES = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
    };

    private static final int[] BALANCED_ORDER = {
            8, 4, 12, 2, 6,
            10, 14, 1, 3, 5,
            7, 9, 11, 13, 15
    };

    private static ShapeBst buildSortedTree() {
        ShapeBst tree = new ShapeBst();

        for (int value : VALUES) {
            tree.add(value);
        }

        return tree;
    }

    private static ShapeBst buildBalancedTree() {
        ShapeBst tree = new ShapeBst();

        for (int value : BALANCED_ORDER) {
            tree.add(value);
        }

        return tree;
    }

    private static void report(
            String name,
            ShapeBst tree) {

        System.out.println(
                "=== " + name + " ===");

        System.out.println(
                "size=" + tree.size());

        System.out.println(
                "height=" + tree.height());

        System.out.print("inorder=");
        tree.inorder();

        int total = 0;

        for (int value : VALUES) {
            int comparisons =
                    tree.searchComparisons(value);

            total += comparisons;

            System.out.println(
                    "search " + value
                    + " comparisons="
                    + comparisons);
        }

        double average =
                (double) total / VALUES.length;

        System.out.println(
                "totalComparisons=" + total);

        System.out.println(
                "averageComparisons="
                        + average);

        System.out.println();
    }

    public static void main(String[] args) {

        ShapeBst sortedTree =
                buildSortedTree();

        ShapeBst balancedTree =
                buildBalancedTree();

        report(
                "sorted insertion",
                sortedTree);

        report(
                "balanced insertion",
                balancedTree);
    }
}