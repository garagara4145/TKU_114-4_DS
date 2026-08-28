class CountNode {
    int key;
    int count;
    CountNode left;
    CountNode right;

    CountNode(int key) {
        this.key = key;
        this.count = 1;
    }
}

class DuplicateBst {
    private CountNode root;

    boolean add(int key) {
        if (root == null) {
            root = new CountNode(key);
            return true;
        }

        CountNode current = root;

        while (true) {
            if (key == current.key) {
                current.count++;
                return false;
            }

            if (key < current.key) {
                if (current.left == null) {
                    current.left = new CountNode(key);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new CountNode(key);
                    return true;
                }

                current = current.right;
            }
        }
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(CountNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(
                node.key + "(" + node.count + ") ");
        inorder(node.right);
    }
}

public class BstDuplicateCounter {
    public static void main(String[] args) {
        DuplicateBst tree = new DuplicateBst();

        int[] values = {
                50, 30, 70, 30, 50, 50, 20, 70
        };

        for (int value : values) {
            System.out.println(
                    "add " + value
                    + " = " + tree.add(value));
        }

        System.out.println("inorder:");
        tree.inorder();
    }
}