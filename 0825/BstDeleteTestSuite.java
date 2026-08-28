import java.util.ArrayList;
import java.util.List;

class TestNode {
    int value;
    TestNode left;
    TestNode right;

    TestNode(int value) {
        this.value = value;
    }
}

class TestBst {
    private TestNode root;
    private int size;

    boolean add(int value) {
        if (root == null) {
            root = new TestNode(value);
            size++;
            return true;
        }

        TestNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new TestNode(value);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TestNode(value);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }

        root = removeNode(root, value);
        size--;
        return true;
    }

    private TestNode removeNode(
            TestNode node,
            int value) {

        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left =
                    removeNode(node.left, value);

        } else if (value > node.value) {
            node.right =
                    removeNode(node.right, value);

        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            TestNode successor =
                    minimumNode(node.right);

            node.value = successor.value;

            node.right =
                    removeNode(
                            node.right,
                            successor.value);
        }

        return node;
    }

    private TestNode minimumNode(TestNode node) {
        TestNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    boolean contains(int value) {
        TestNode current = root;

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

    int size() {
        return size;
    }

    List<Integer> inorder() {
        List<Integer> result =
                new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private void inorder(
            TestNode node,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    boolean isValid() {
        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            TestNode node,
            long low,
            long high) {

        if (node == null) {
            return true;
        }

        if (node.value <= low
                || node.value >= high) {
            return false;
        }

        return isValid(
                node.left,
                low,
                node.value)
                && isValid(
                        node.right,
                        node.value,
                        high);
    }
}

public class BstDeleteTestSuite {

    private static void printResult(
            String testName,
            TestBst tree) {

        System.out.println(
                "=== " + testName + " ===");

        System.out.println(
                "inorder=" + tree.inorder());

        System.out.println(
                "size=" + tree.size());

        System.out.println(
                "valid=" + tree.isValid());

        System.out.println();
    }

    public static void main(String[] args) {

        // 1. 空樹
        TestBst emptyTree = new TestBst();

        System.out.println(
                "empty remove="
                        + emptyTree.remove(10));

        printResult(
                "empty tree",
                emptyTree);

        // 2. 缺失值
        TestBst missingTree = new TestBst();

        missingTree.add(50);
        missingTree.add(30);
        missingTree.add(70);

        System.out.println(
                "missing remove="
                        + missingTree.remove(999));

        printResult(
                "missing value",
                missingTree);

        // 3. 單根
        TestBst singleRoot = new TestBst();

        singleRoot.add(50);

        System.out.println(
                "single root remove="
                        + singleRoot.remove(50));

        printResult(
                "single root",
                singleRoot);

        // 4. 一子根
        TestBst oneChildRoot = new TestBst();

        oneChildRoot.add(50);
        oneChildRoot.add(30);

        System.out.println(
                "one-child root remove="
                        + oneChildRoot.remove(50));

        printResult(
                "one-child root",
                oneChildRoot);

        // 5. 二子根
        TestBst twoChildRoot = new TestBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {

            twoChildRoot.add(value);
        }

        System.out.println(
                "two-child root remove="
                        + twoChildRoot.remove(50));

        printResult(
                "two-child root",
                twoChildRoot);

        // 6. 連續刪除直到空
        TestBst continuousTree = new TestBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {

            continuousTree.add(value);
        }

        System.out.println(
                "=== continuous delete ===");

        int[] deleteOrder = {
                20, 40, 30, 60, 80, 70, 50
        };

        for (int value : deleteOrder) {

            System.out.println(
                    "remove " + value
                            + " = "
                            + continuousTree.remove(value));

            System.out.println(
                    "inorder="
                            + continuousTree.inorder());

            System.out.println(
                    "size="
                            + continuousTree.size());

            System.out.println(
                    "valid="
                            + continuousTree.isValid());

            System.out.println();
        }
    }
}