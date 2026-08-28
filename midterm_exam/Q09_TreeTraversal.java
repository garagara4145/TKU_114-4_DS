import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q09_TreeTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private static void preorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static void inorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> walkRecordP09 = new ArrayList<>();
        postorder(root, walkRecordP09);
        return walkRecordP09;
    }

    private static void postorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    public static List<Integer> levelOrder(Node root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(8);

        root.left = new Node(4);
        root.right = new Node(12);

        root.left.left = new Node(2);
        root.left.right = new Node(6);

        root.right.right = new Node(14);

        System.out.println("preorder = " + preorder(root));
        System.out.println("inorder = " + inorder(root));
        System.out.println("postorder = " + postorder(root));
        System.out.println("level = " + levelOrder(root));

        System.out.println("empty preorder = " + preorder(null));
        System.out.println("empty inorder = " + inorder(null));
        System.out.println("empty postorder = " + postorder(null));
        System.out.println("empty level = " + levelOrder(null));
    }
}