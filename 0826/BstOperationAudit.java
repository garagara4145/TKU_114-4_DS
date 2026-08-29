import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

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

        boolean remove(int value) {
            if (!contains(value)) {
                return false;
            }

            root = remove(root, value);
            return true;
        }

        private Node remove(Node node, int value) {
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

                Node successor = minimum(node.right);
                node.value = successor.value;
                node.right = remove(node.right, successor.value);
            }

            return node;
        }

        private Node minimum(Node node) {
            while (node.left != null) {
                node = node.left;
            }

            return node;
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

        List<Integer> inorder() {
            List<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(Node node, List<Integer> result) {
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

            return 1 + size(node.left) + size(node.right);
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

        boolean isValid() {
            return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isValid(Node node, long low, long high) {
            if (node == null) {
                return true;
            }

            if (node.value <= low || node.value >= high) {
                return false;
            }

            return isValid(node.left, low, node.value)
                    && isValid(node.right, node.value, high);
        }
    }

    static void audit(Bst tree, String operation, boolean result) {
        System.out.println(
                "operation=" + operation
                + ", result=" + result
                + ", inorder=" + tree.inorder()
                + ", size=" + tree.size()
                + ", height=" + tree.height()
                + ", valid=" + tree.isValid()
        );
    }

    public static void main(String[] args) {

        Bst tree = new Bst();

        audit(tree, "add 50", tree.add(50));
        audit(tree, "add 30", tree.add(30));
        audit(tree, "add 70", tree.add(70));
        audit(tree, "add 20", tree.add(20));
        audit(tree, "add 40", tree.add(40));
        audit(tree, "add 60", tree.add(60));
        audit(tree, "add 80", tree.add(80));

        audit(tree, "add duplicate 50", tree.add(50));

        audit(tree, "remove missing 999", tree.remove(999));

        audit(tree, "remove leaf 20", tree.remove(20));

        audit(tree, "remove one-child 30", tree.remove(30));

        audit(tree, "remove two-children 70", tree.remove(70));
    }
}