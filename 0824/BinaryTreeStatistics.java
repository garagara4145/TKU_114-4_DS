class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}

public class BinaryTreeStatistics {

    static int size(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    static int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.value + sum(node.left) + sum(node.right);
    }

    static int maximum(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("empty tree");
        }

        return maximumValue(node);
    }

    static int maximumValue(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int leftMax = maximumValue(node.left);
        int rightMax = maximumValue(node.right);

        return Math.max(node.value, Math.max(leftMax, rightMax));
    }

    static int countLeaves(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeaves(node.left) + countLeaves(node.right);
    }

    static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    static boolean contains(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.value == target) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        System.out.println("size=" + size(root));
        System.out.println("sum=" + sum(root));
        System.out.println("maximum=" + maximum(root));
        System.out.println("leaves=" + countLeaves(root));
        System.out.println("height=" + height(root));
        System.out.println("contains 7=" + contains(root, 7));
        System.out.println("contains 99=" + contains(root, 99));

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("empty maximum=" + e.getMessage());
        }
    }
}