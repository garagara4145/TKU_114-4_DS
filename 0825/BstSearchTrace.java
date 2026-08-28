class TraceNode {
    int value;
    TraceNode left;
    TraceNode right;

    TraceNode(int value) {
        this.value = value;
    }
}

class TraceBst {
    private TraceNode root;

    boolean add(int value) {
        if (root == null) {
            root = new TraceNode(value);
            return true;
        }

        TraceNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new TraceNode(value);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TraceNode(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    void search(int target) {
        TraceNode current = root;
        int comparisons = 0;

        while (current != null) {
            comparisons++;

            if (target == current.value) {
                System.out.println(
                        "current=" + current.value
                        + ", direction=FOUND"
                        + ", comparisons=" + comparisons);
                System.out.println(
                        "result=found " + target);
                return;
            }

            if (target < current.value) {
                System.out.println(
                        "current=" + current.value
                        + ", direction=LEFT"
                        + ", comparisons=" + comparisons);
                current = current.left;
            } else {
                System.out.println(
                        "current=" + current.value
                        + ", direction=RIGHT"
                        + ", comparisons=" + comparisons);
                current = current.right;
            }
        }

        System.out.println(
                "result=missing " + target
                + ", comparisons=" + comparisons);
    }
}

public class BstSearchTrace {
    public static void main(String[] args) {
        TraceBst tree = new TraceBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }

        System.out.println("=== root ===");
        tree.search(50);

        System.out.println();

        System.out.println("=== leaf ===");
        tree.search(20);

        System.out.println();

        System.out.println("=== internal ===");
        tree.search(30);

        System.out.println();

        System.out.println("=== missing ===");
        tree.search(65);
    }
}