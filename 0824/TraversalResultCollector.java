import java.util.ArrayList;
import java.util.List;

class TraversalNode {
    String value;
    TraversalNode left;
    TraversalNode right;

    TraversalNode(String value) {
        this.value = value;
    }
}

public class TraversalResultCollector {

    static List<String> preorder(TraversalNode node) {
        List<String> result = new ArrayList<>();
        preorder(node, result);
        return result;
    }

    static void preorder(TraversalNode node, List<String> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<String> inorder(TraversalNode node) {
        List<String> result = new ArrayList<>();
        inorder(node, result);
        return result;
    }

    static void inorder(TraversalNode node, List<String> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    static List<String> postorder(TraversalNode node) {
        List<String> result = new ArrayList<>();
        postorder(node, result);
        return result;
    }

    static void postorder(TraversalNode node, List<String> result) {
        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    static List<String> levelOrder(TraversalNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<TraversalNode> queue = new ArrayList<>();
        queue.add(root);

        int index = 0;

        while (index < queue.size()) {
            TraversalNode current = queue.get(index++);
            result.add(current.value);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    static void printTest(String name, TraversalNode root) {
        System.out.println(name);
        System.out.println("preorder=" + preorder(root));
        System.out.println("inorder=" + inorder(root));
        System.out.println("postorder=" + postorder(root));
        System.out.println("levelOrder=" + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        TraversalNode empty = null;

        TraversalNode single = new TraversalNode("A");

        TraversalNode leftSkewed = new TraversalNode("A");
        leftSkewed.left = new TraversalNode("B");
        leftSkewed.left.left = new TraversalNode("C");

        TraversalNode complete = new TraversalNode("A");
        complete.left = new TraversalNode("B");
        complete.right = new TraversalNode("C");
        complete.left.left = new TraversalNode("D");
        complete.left.right = new TraversalNode("E");
        complete.right.left = new TraversalNode("F");
        complete.right.right = new TraversalNode("G");

        printTest("empty", empty);
        printTest("single-node", single);
        printTest("left-skewed", leftSkewed);
        printTest("complete", complete);
    }
}