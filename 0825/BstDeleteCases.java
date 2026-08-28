class DeleteNode {
    int value;
    DeleteNode left;
    DeleteNode right;

    DeleteNode(int value) {
        this.value = value;
    }
}

class DeleteBst {
    private DeleteNode root;
    private int size;

    boolean add(int value) {
        if (root == null) {
            root = new DeleteNode(value);
            size++;
            return true;
        }

        DeleteNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new DeleteNode(value);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new DeleteNode(value);
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

        root = remove(root, value);
        size--;
        return true;
    }

    private DeleteNode remove(DeleteNode node, int value) {
        if (node == null) {
            return null;
        }

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

            DeleteNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }

        return node;
    }

    private DeleteNode minimumNode(DeleteNode node) {
        DeleteNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    boolean contains(int value) {
        DeleteNode current = root;

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

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(DeleteNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    boolean isValid() {
        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            DeleteNode node,
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

public class BstDeleteCases {

    private static void printState(
            DeleteBst tree) {

        System.out.print("inorder=");
        tree.inorder();

        System.out.println(
                "size=" + tree.size());

        System.out.println(
                "valid=" + tree.isValid());

        System.out.println();
    }

    public static void main(String[] args) {

        DeleteBst tree = new DeleteBst();

        for (int value : new int[]{
                50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }

        System.out.println("=== initial ===");
        printState(tree);

        System.out.println("=== delete leaf 20 ===");
        System.out.println(
                "remove=" + tree.remove(20));
        printState(tree);

        System.out.println("=== delete one-child 30 ===");
        System.out.println(
                "remove=" + tree.remove(30));
        printState(tree);

        System.out.println("=== delete two-child 50 ===");
        System.out.println(
                "remove=" + tree.remove(50));
        printState(tree);

        System.out.println("=== delete missing 999 ===");
        System.out.println(
                "remove=" + tree.remove(999));
        printState(tree);
    }
}