class SkewNode {
    int value;
    SkewNode left;
    SkewNode right;

    SkewNode(int value) {
        this.value = value;
    }
}

class SkewBst {
    private SkewNode root;

    boolean add(int value) {
        if (root == null) {
            root = new SkewNode(value);
            return true;
        }

        SkewNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new SkewNode(value);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new SkewNode(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    int size() {
        return size(root);
    }

    private int size(SkewNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(SkewNode node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    int searchComparisons(int target) {
        SkewNode current = root;
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

    private void inorder(SkewNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class SkewedBstReport {

    private static final int[] SORTED = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
    };

    private static final int[] BALANCED = {
            80, 40, 120, 20, 60,
            100, 140, 10, 30, 50,
            70, 90, 110, 130, 150
    };

    private static SkewBst buildTree(int[] values) {
        SkewBst tree = new SkewBst();

        for (int value : values) {
            tree.add(value);
        }

        return tree;
    }

    private static void printReport(
            String name,
            SkewBst tree) {

        System.out.println("=== " + name + " ===");

        System.out.println(
                "size=" + tree.size());

        System.out.println(
                "height=" + tree.height());

        System.out.print("inorder=");
        tree.inorder();

        int[] targets = {
                10, 50, 80, 100, 150
        };

        for (int target : targets) {
            System.out.println(
                    "search " + target
                    + " comparisons="
                    + tree.searchComparisons(target));
        }

        System.out.println();
    }

    public static void main(String[] args) {

        SkewBst sortedTree =
                buildTree(SORTED);

        SkewBst balancedTree =
                buildTree(BALANCED);

        printReport(
                "sorted insertion",
                sortedTree);

        printReport(
                "balanced insertion",
                balancedTree);
    }
}