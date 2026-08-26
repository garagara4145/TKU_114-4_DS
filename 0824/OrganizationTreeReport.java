import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class OrganizationNode {
    String name;
    OrganizationNode left;
    OrganizationNode right;

    OrganizationNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {

    static OrganizationNode findParent(OrganizationNode root, String target) {
        if (root == null || target == null) {
            return null;
        }

        if ((root.left != null && root.left.name.equals(target))
                || (root.right != null && root.right.name.equals(target))) {
            return root;
        }

        OrganizationNode leftResult = findParent(root.left, target);

        if (leftResult != null) {
            return leftResult;
        }

        return findParent(root.right, target);
    }

    static int findDepth(OrganizationNode root, String target) {
        return findDepth(root, target, 0);
    }

    static int findDepth(OrganizationNode node, String target, int depth) {
        if (node == null || target == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftDepth = findDepth(node.left, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(node.right, target, depth + 1);
    }

    static List<String> pathFromRoot(OrganizationNode root, String target) {
        List<String> path = new ArrayList<>();

        if (findPath(root, target, path)) {
            return path;
        }

        return new ArrayList<>();
    }

    static boolean findPath(
            OrganizationNode node,
            String target,
            List<String> path) {

        if (node == null || target == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (findPath(node.left, target, path)
                || findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    static void printByLevel(OrganizationNode root) {
        if (root == null) {
            System.out.println("empty organization");
            return;
        }

        Deque<OrganizationNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("level " + level + ": ");

            for (int i = 0; i < count; i++) {
                OrganizationNode current = queue.poll();

                System.out.print(current.name + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        OrganizationNode root = new OrganizationNode("CEO");

        root.left = new OrganizationNode("Engineering");
        root.right = new OrganizationNode("Sales");

        root.left.left = new OrganizationNode("Backend");
        root.left.right = new OrganizationNode("Frontend");

        root.right.left = new OrganizationNode("Marketing");
        root.right.right = new OrganizationNode("Support");

        root.left.left.left = new OrganizationNode("Database");

        OrganizationNode parent = findParent(root, "Database");

        System.out.println("parent of Database="
                + (parent == null ? "null" : parent.name));

        System.out.println("depth Backend="
                + findDepth(root, "Backend"));

        System.out.println("depth Database="
                + findDepth(root, "Database"));

        System.out.println("depth Unknown="
                + findDepth(root, "Unknown"));

        System.out.println("path to Database="
                + pathFromRoot(root, "Database"));

        System.out.println("path to Unknown="
                + pathFromRoot(root, "Unknown"));

        printByLevel(root);
    }
}