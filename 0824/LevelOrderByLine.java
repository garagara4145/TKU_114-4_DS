import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode(String value) {
        this.value = value;
    }
}

public class LevelOrderByLine {

    static void levelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 1;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("level " + level + " count=" + count + ": ");

            for (int i = 0; i < count; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.value + " ");

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
        TreeNode root = new TreeNode("M");

        root.left = new TreeNode("F");
        root.left.left = new TreeNode("B");

        root.right = new TreeNode("T");
        root.right.left = new TreeNode("R");
        root.right.right = new TreeNode("Z");

        levelOrder(root);

        System.out.println("empty test:");
        levelOrder(null);
    }
}