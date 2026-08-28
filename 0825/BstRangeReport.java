class RangeNode {
    int value;
    RangeNode left;
    RangeNode right;

    RangeNode(int value) {
        this.value = value;
    }
}

class RangeBst {
    private RangeNode root;

    boolean add(int value) {
        if (root == null) {
            root = new RangeNode(value);
            return true;
        }

        RangeNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new RangeNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new RangeNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    Integer min() {
        if (root == null) {
            return null;
        }

        RangeNode current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    Integer max() {
        if (root == null) {
            return null;
        }

        RangeNode current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    void printRange(int low, int high) {
        if (low > high) {
            System.out.println();
            return;
        }

        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(
            RangeNode node,
            int low,
            int high) {

        if (node == null) {
            return;
        }

        if (node.value > low) {
            printRange(node.left, low, high);
        }

        if (node.value >= low && node.value <= high) {
            System.out.print(node.value + " ");
        }

        if (node.value < high) {
            printRange(node.right, low, high);
        }
    }
}

public class BstRangeReport {
    public static void main(String[] args) {
        RangeBst tree = new RangeBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }

        System.out.println("min=" + tree.min());
        System.out.println("max=" + tree.max());

        System.out.print("range 30..70=");
        tree.printRange(30, 70);

        System.out.print("range 20..40=");
        tree.printRange(20, 40);

        System.out.print("range 55..75=");
        tree.printRange(55, 75);

        System.out.print("range 90..100=");
        tree.printRange(90, 100);

        System.out.print("range 70..30=");
        tree.printRange(70, 30);

        RangeBst empty = new RangeBst();

        System.out.println("empty min=" + empty.min());
        System.out.println("empty max=" + empty.max());

        System.out.print("empty range=");
        empty.printRange(1, 10);
    }
}