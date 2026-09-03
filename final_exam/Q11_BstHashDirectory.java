import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {

    private Node root;
    private Map<Integer, String> names;
    private int count;

    private static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    public Q11_BstHashDirectory() {
        root = null;
        names = new HashMap<>();
        count = 0;
    }

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }

        name = name.trim();

        if (name.isEmpty()) {
            return false;
        }

        if (names.containsKey(id)) {
            return false;
        }

        root = insert(root, id);
        names.put(id, name);
        count++;

        return true;
    }

    private Node insert(Node node, int id) {
        if (node == null) {
            return new Node(id);
        }

        if (id < node.id) {
            node.left = insert(node.left, id);
        } else if (id > node.id) {
            node.right = insert(node.right, id);
        }

        return node;
    }

    public String findName(int id) {
        if (!names.containsKey(id)) {
            return null;
        }

        return names.get(id);
    }

    public boolean remove(int id) {
        if (!names.containsKey(id)) {
            return false;
        }

        root = delete(root, id);
        names.remove(id);
        count--;

        return true;
    }

    private Node delete(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.id) {
            node.left = delete(node.left, id);
        } else if (id > node.id) {
            node.right = delete(node.right, id);
        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.id = successor.id;
            node.right = delete(node.right, successor.id);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        rangeSearch(root, low, high, result);

        return result;
    }

    private void rangeSearch(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.id > low) {
            rangeSearch(node.left, low, high, result);
        }

        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }

        if (node.id < high) {
            rangeSearch(node.right, low, high, result);
        }
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {

        Q11_BstHashDirectory directory =
                new Q11_BstHashDirectory();

        System.out.println("新增A "
                + directory.add(50, " Alice "));

        System.out.println("新增B "
                + directory.add(30, "Bob"));

        System.out.println("新增C "
                + directory.add(70, "Charlie"));

        System.out.println("新增D "
                + directory.add(20, "David"));

        System.out.println("新增E "
                + directory.add(40, "Eve"));

        System.out.println("查詢50  "
                + directory.findName(50));

        System.out.println("查詢30 "
                + directory.findName(30));

        System.out.println("查詢不存在的 99 "
                + directory.findName(99));

        System.out.println("重複新增 30 "
                + directory.add(30, "New"));

        System.out.println("ID範圍 20~50  "
                + directory.idsBetween(20, 50));

        System.out.println("ID範圍 35~70  "
                + directory.idsBetween(35, 70));

        System.out.println("數量  "
                + directory.size());

        System.out.println("刪除30  "
                + directory.remove(30));

        System.out.println("查詢30  "
                + directory.findName(30));

        System.out.println("ID範圍 20~70 -> "
                + directory.idsBetween(20, 70));

        System.out.println("刪除不存在的 99  "
                + directory.remove(99));

        System.out.println("low 大於 high  "
                + directory.idsBetween(70, 20));

        System.out.println("數量  "
                + directory.size());
    }
}