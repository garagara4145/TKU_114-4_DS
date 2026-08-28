class InvariantNode {
    int value;
    InvariantNode left;
    InvariantNode right;

    InvariantNode(int value) {
        this.value = value;
    }
}

class InvariantBst {

    private InvariantNode root;

    boolean add(int value) {
        if (root == null) {
            root = new InvariantNode(value);
            return true;
        }

        InvariantNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new InvariantNode(value);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new InvariantNode(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    boolean isValid() {
        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            InvariantNode node,
            long minimum,
            long maximum) {

        if (node == null) {
            return true;
        }

        if (node.value <= minimum
                || node.value >= maximum) {
            return false;
        }

        return isValid(
                node.left,
                minimum,
                node.value)
                && isValid(
                        node.right,
                        node.value,
                        maximum);
    }
}

public class BstInvariantChecker {

    private static void printResult(
            String name,
            InvariantBst tree) {

        System.out.println(
                name + " = " + tree.isValid());
    }

    public static void main(String[] args) {

        InvariantBst validTree = new InvariantBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {
            validTree.add(value);
        }

        printResult(
                "valid tree",
                validTree);

        InvariantNode wrongLeft =
                new InvariantNode(50);

        wrongLeft.left =
                new InvariantNode(70);

        InvariantBst invalidTree1 =
                new InvariantBst();

        printResult(
                "invalid tree 1",
                invalidTree1);

        InvariantNode wrongDeep =
                new InvariantNode(50);

        wrongDeep.left =
                new InvariantNode(30);

        wrongDeep.left.right =
                new InvariantNode(60);

        InvariantBst invalidTree2 =
                new InvariantBst();

        printResult(
                "invalid tree 2",
                invalidTree2);

        InvariantNode wrongRight =
                new InvariantNode(50);

        wrongRight.right =
                new InvariantNode(70);

        wrongRight.right.left =
                new InvariantNode(40);

        InvariantBst invalidTree3 =
                new InvariantBst();

        printResult(
                "invalid tree 3",
                invalidTree3);

        InvariantBst emptyTree =
                new InvariantBst();

        printResult(
                "empty tree",
                emptyTree);
    }
}