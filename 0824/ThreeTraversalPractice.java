class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode(String value) {
        this.value = value;
    }
}

public class ThreeTraversalPractice {

    static void preorder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    static void postorder(TreeNode node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("M");

        root.left = new TreeNode("F");
        root.left.left = new TreeNode("B");

        root.right = new TreeNode("T");
        root.right.left = new TreeNode("R");
        root.right.right = new TreeNode("Z");

        System.out.print("preorder=");
        preorder(root);
        System.out.println();

        System.out.print("inorder=");
        inorder(root);
        System.out.println();

        System.out.print("postorder=");
        postorder(root);
        System.out.println();
    }
}