import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class ReportNode {
    String value;
    ReportNode left;
    ReportNode right;

    ReportNode(String value) {
        this.value = value;
    }
}

public class TraversalTestReport {

    static List<String> preorder(ReportNode node) {
        List<String> result = new ArrayList<>();
        preorder(node, result);
        return result;
    }

    static void preorder(ReportNode node, List<String> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<String> inorder(ReportNode node) {
        List<String> result = new ArrayList<>();
        inorder(node, result);
        return result;
    }

    static void inorder(ReportNode node, List<String> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    static List<String> postorder(ReportNode node) {
        List<String> result = new ArrayList<>();
        postorder(node, result);
        return result;
    }

    static void postorder(ReportNode node, List<String> result) {
        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    static List<String> levelOrder(ReportNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<ReportNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ReportNode current = queue.poll();
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

    static void test(
            String name,
            ReportNode root,
            List<String> expectedPreorder,
            List<String> expectedInorder,
            List<String> expectedPostorder,
            List<String> expectedLevelOrder) {

        List<String> actualPreorder = preorder(root);
        List<String> actualInorder = inorder(root);
        List<String> actualPostorder = postorder(root);
        List<String> actualLevelOrder = levelOrder(root);

        System.out.println("=== " + name + " ===");

        System.out.println("preorder expected=" + expectedPreorder);
        System.out.println("preorder actual=" + actualPreorder);
        System.out.println("preorder pass="
                + expectedPreorder.equals(actualPreorder));

        System.out.println("inorder expected=" + expectedInorder);
        System.out.println("inorder actual=" + actualInorder);
        System.out.println("inorder pass="
                + expectedInorder.equals(actualInorder));

        System.out.println("postorder expected=" + expectedPostorder);
        System.out.println("postorder actual=" + actualPostorder);
        System.out.println("postorder pass="
                + expectedPostorder.equals(actualPostorder));

        System.out.println("levelOrder expected=" + expectedLevelOrder);
        System.out.println("levelOrder actual=" + actualLevelOrder);
        System.out.println("levelOrder pass="
                + expectedLevelOrder.equals(actualLevelOrder));

        System.out.println();
    }

    public static void main(String[] args) {

        ReportNode empty = null;

        ReportNode single = new ReportNode("A");

        ReportNode onlyLeft = new ReportNode("A");
        onlyLeft.left = new ReportNode("B");
        onlyLeft.left.left = new ReportNode("C");

        ReportNode onlyRight = new ReportNode("A");
        onlyRight.right = new ReportNode("B");
        onlyRight.right.right = new ReportNode("C");

        ReportNode complete = new ReportNode("A");
        complete.left = new ReportNode("B");
        complete.right = new ReportNode("C");
        complete.left.left = new ReportNode("D");
        complete.left.right = new ReportNode("E");
        complete.right.left = new ReportNode("F");
        complete.right.right = new ReportNode("G");

        ReportNode irregular = new ReportNode("A");
        irregular.left = new ReportNode("B");
        irregular.right = new ReportNode("C");
        irregular.left.right = new ReportNode("D");
        irregular.right.left = new ReportNode("E");
        irregular.right.left.right = new ReportNode("F");

        test(
                "empty",
                empty,
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );

        test(
                "single-node",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A")
        );

        test(
                "only-left",
                onlyLeft,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        test(
                "only-right",
                onlyRight,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        test(
                "complete",
                complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F", "G")
        );

        test(
                "irregular",
                irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "E", "F", "C"),
                List.of("D", "B", "F", "E", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F")
        );
    }
}