import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean directory;
        long size;
        List<Node> children;

        Node(String name, boolean directory, long size) {
            this.name = name;
            this.directory = directory;
            this.size = size;
            this.children = new ArrayList<>();
        }

        void addChild(Node child) {
            children.add(child);
        }
    }

    static long calculateSize(Node node) {
        if (!node.directory) {
            return node.size;
        }

        long total = 0;

        for (Node child : node.children) {
            total += calculateSize(child);
        }

        node.size = total;

        return total;
    }

    static int countNodes(Node node) {
        int count = 1;

        for (Node child : node.children) {
            count += countNodes(child);
        }

        return count;
    }

    static int countFiles(Node node) {
        if (!node.directory) {
            return 1;
        }

        int count = 0;

        for (Node child : node.children) {
            count += countFiles(child);
        }

        return count;
    }

    static int countDirectories(Node node) {
        if (!node.directory) {
            return 0;
        }

        int count = 1;

        for (Node child : node.children) {
            count += countDirectories(child);
        }

        return count;
    }

    static int height(Node node) {
        if (node.children.isEmpty()) {
            return 0;
        }

        int maxHeight = -1;

        for (Node child : node.children) {
            maxHeight = Math.max(
                    maxHeight,
                    height(child)
            );
        }

        return maxHeight + 1;
    }

    static Node largestFile(Node node) {
        if (!node.directory) {
            return node;
        }

        Node largest = null;

        for (Node child : node.children) {
            Node candidate = largestFile(child);

            if (candidate != null) {
                if (largest == null ||
                        candidate.size > largest.size) {
                    largest = candidate;
                }
            }
        }

        return largest;
    }

    static void printTree(Node node, String indent) {
        System.out.println(
                indent + node.name +
                (node.directory ? "/" : " (" + node.size + " KB)")
        );

        for (Node child : node.children) {
            printTree(child, indent + "  ");
        }
    }

    public static void main(String[] args) {

        Node root =
                new Node("Project", true, 0);

        Node src =
                new Node("src", true, 0);

        Node data =
                new Node("data", true, 0);

        Node readme =
                new Node("README.md", false, 10);

        Node main =
                new Node("Main.java", false, 20);

        Node bst =
                new Node("BST.java", false, 30);

        Node students =
                new Node("students.csv", false, 50);

        Node scores =
                new Node("scores.csv", false, 80);

        root.addChild(src);
        root.addChild(data);
        root.addChild(readme);

        src.addChild(main);
        src.addChild(bst);

        data.addChild(students);
        data.addChild(scores);

        calculateSize(root);

        System.out.println("--- Directory Tree ---");
        printTree(root, "");

        System.out.println();

        System.out.println("--- Report ---");

        System.out.println(
                "total node=" + countNodes(root)
        );

        System.out.println(
                "file count=" + countFiles(root)
        );

        System.out.println(
                "directory count=" + countDirectories(root)
        );

        System.out.println(
                "height=" + height(root)
        );

        Node largest = largestFile(root);

        System.out.println(
                "largest file=" +
                largest.name +
                " (" +
                largest.size +
                " KB)"
        );

        System.out.println(
                "total size=" +
                root.size +
                " KB"
        );
    }
}