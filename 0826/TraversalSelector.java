public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static String preorder(Node node) {
        if (node == null) {
            return "";
        }

        String result = node.value;

        String left = preorder(node.left);
        String right = preorder(node.right);

        if (!left.isEmpty()) {
            result += " " + left;
        }

        if (!right.isEmpty()) {
            result += " " + right;
        }

        return result;
    }

    static String inorder(Node node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "("
                + inorder(node.left)
                + " " + node.value + " "
                + inorder(node.right)
                + ")";
    }

    static String postorder(Node node) {
        if (node == null) {
            return "";
        }

        String left = postorder(node.left);
        String right = postorder(node.right);

        String result = "";

        if (!left.isEmpty()) {
            result += left + " ";
        }

        if (!right.isEmpty()) {
            result += right + " ";
        }

        result += node.value;

        return result;
    }

    public static void main(String[] args) {

       

        Node root = new Node("*");

        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("3");
        root.left.right = new Node("5");

        root.right.left = new Node("8");
        root.right.right = new Node("2");

        System.out.println("--- Expression Tree ---");

        System.out.println("Preorder  = " + preorder(root));
        System.out.println("Inorder   = " + inorder(root));
        System.out.println("Postorder = " + postorder(root));
    }
}