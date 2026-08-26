class FolderNode {
    String name;
    int ownSize;
    FolderNode left;
    FolderNode right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {

    static int calculateSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = calculateSize(node.left);
        int rightSize = calculateSize(node.right);

        return node.ownSize + leftSize + rightSize;
    }

    static FolderNode findLargest(FolderNode node) {
        if (node == null) {
            return null;
        }

        calculateSize(node);

        FolderNode largest = node;

        FolderNode leftLargest = findLargest(node.left);
        FolderNode rightLargest = findLargest(node.right);

        if (leftLargest != null
                && calculateSize(leftLargest) > calculateSize(largest)) {
            largest = leftLargest;
        }

        if (rightLargest != null
                && calculateSize(rightLargest) > calculateSize(largest)) {
            largest = rightLargest;
        }

        return largest;
    }

    static void printLeafFolders(FolderNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(node.name + " size=" + calculateSize(node));
            return;
        }

        printLeafFolders(node.left);
        printLeafFolders(node.right);
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("root", 100);

        root.left = new FolderNode("documents", 50);
        root.right = new FolderNode("pictures", 80);

        root.left.left = new FolderNode("homework", 30);
        root.left.right = new FolderNode("notes", 20);

        root.right.left = new FolderNode("photos", 60);
        root.right.right = new FolderNode("icons", 10);

        int totalSize = calculateSize(root);
        FolderNode largest = findLargest(root);

        System.out.println("total size=" + totalSize);
        System.out.println("largest subtree="
                + largest.name + " size=" + calculateSize(largest));

        System.out.println("leaf folders:");
        printLeafFolders(root);
    }
}