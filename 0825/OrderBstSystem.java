import java.util.ArrayList;
import java.util.List;

class Order {
    private final int orderId;
    private final String customer;
    private int quantity;

    Order(int orderId, String customer, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.quantity = Math.max(0, quantity);
    }

    int getOrderId() {
        return orderId;
    }

    String getCustomer() {
        return customer;
    }

    int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
    }

    @Override
    public String toString() {
        return orderId + "|" + customer + "|qty=" + quantity;
    }
}

class OrderNode {
    Order data;
    OrderNode left;
    OrderNode right;

    OrderNode(Order data) {
        this.data = data;
    }
}

class OrderBst {
    private OrderNode root;

    boolean add(Order order) {
        if (order == null) {
            return false;
        }

        if (root == null) {
            root = new OrderNode(order);
            return true;
        }

        OrderNode current = root;

        while (true) {
            if (order.getOrderId() == current.data.getOrderId()) {
                return false;
            }

            if (order.getOrderId() < current.data.getOrderId()) {
                if (current.left == null) {
                    current.left = new OrderNode(order);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new OrderNode(order);
                    return true;
                }

                current = current.right;
            }
        }
    }

    Order find(int orderId) {
        OrderNode current = root;

        while (current != null) {
            if (orderId == current.data.getOrderId()) {
                return current.data;
            }

            if (orderId < current.data.getOrderId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean cancel(int orderId) {
        if (find(orderId) == null) {
            return false;
        }

        root = remove(root, orderId);
        return true;
    }

    private OrderNode remove(
            OrderNode node,
            int orderId) {

        if (node == null) {
            return null;
        }

        if (orderId < node.data.getOrderId()) {
            node.left =
                    remove(node.left, orderId);
        } else if (orderId > node.data.getOrderId()) {
            node.right =
                    remove(node.right, orderId);
        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            OrderNode successor =
                    minimumNode(node.right);

            node.data = successor.data;

            node.right =
                    remove(
                            node.right,
                            successor.data.getOrderId());
        }

        return node;
    }

    private OrderNode minimumNode(OrderNode node) {
        OrderNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    boolean updateQuantity(
            int orderId,
            int quantity) {

        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (quantity < 0) {
            return false;
        }

        order.setQuantity(quantity);
        return true;
    }

    List<Order> range(
            int lowId,
            int highId) {

        List<Order> result =
                new ArrayList<>();

        if (lowId > highId) {
            return result;
        }

        range(
                root,
                lowId,
                highId,
                result);

        return result;
    }

    private void range(
            OrderNode node,
            int lowId,
            int highId,
            List<Order> result) {

        if (node == null) {
            return;
        }

        int id = node.data.getOrderId();

        if (id > lowId) {
            range(
                    node.left,
                    lowId,
                    highId,
                    result);
        }

        if (id >= lowId && id <= highId) {
            result.add(node.data);
        }

        if (id < highId) {
            range(
                    node.right,
                    lowId,
                    highId,
                    result);
        }
    }

    List<Order> inorder() {
        List<Order> result =
                new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private void inorder(
            OrderNode node,
            List<Order> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.data);
        inorder(node.right, result);
    }

    int size() {
        return size(root);
    }

    private int size(OrderNode node) {
        if (node == null) {
            return 0;
        }

        return 1
                + size(node.left)
                + size(node.right);
    }

    int totalQuantity() {
        return totalQuantity(root);
    }

    private int totalQuantity(OrderNode node) {
        if (node == null) {
            return 0;
        }

        return node.data.getQuantity()
                + totalQuantity(node.left)
                + totalQuantity(node.right);
    }

    boolean isValid() {
        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            OrderNode node,
            long low,
            long high) {

        if (node == null) {
            return true;
        }

        int id = node.data.getOrderId();

        if (id <= low || id >= high) {
            return false;
        }

        return isValid(
                node.left,
                low,
                id)
                && isValid(
                        node.right,
                        id,
                        high);
    }
}

public class OrderBstSystem {

    private static void printSummary(
            OrderBst tree) {

        System.out.println(
                "size=" + tree.size());

        System.out.println(
                "totalQuantity="
                        + tree.totalQuantity());

        System.out.println(
                "valid=" + tree.isValid());

        System.out.println(
                "orders=" + tree.inorder());

        System.out.println();
    }

    public static void main(String[] args) {

        OrderBst tree = new OrderBst();

        System.out.println("=== add ===");

        System.out.println(
                tree.add(
                        new Order(
                                300,
                                "Mina",
                                5)));

        System.out.println(
                tree.add(
                        new Order(
                                100,
                                "Leo",
                                3)));

        System.out.println(
                tree.add(
                        new Order(
                                500,
                                "Nora",
                                8)));

        System.out.println(
                tree.add(
                        new Order(
                                200,
                                "Ivy",
                                4)));

        System.out.println(
                tree.add(
                        new Order(
                                400,
                                "John",
                                2)));

        System.out.println(
                tree.add(
                        new Order(
                                100,
                                "Duplicate",
                                10)));

        printSummary(tree);

        System.out.println("=== find ===");

        System.out.println(
                "find 200="
                        + tree.find(200));

        System.out.println(
                "find 999="
                        + tree.find(999));

        System.out.println();

        System.out.println("=== update ===");

        System.out.println(
                "update 200="
                        + tree.updateQuantity(200, 10));

        System.out.println(
                "update 999="
                        + tree.updateQuantity(999, 5));

        System.out.println(
                "update 200 negative="
                        + tree.updateQuantity(200, -1));

        System.out.println(
                "find 200="
                        + tree.find(200));

        System.out.println();

        System.out.println("=== range ===");

        System.out.println(
                "100..400="
                        + tree.range(100, 400));

        System.out.println(
                "250..450="
                        + tree.range(250, 450));

        System.out.println(
                "500..300="
                        + tree.range(500, 300));

        System.out.println();

        System.out.println("=== cancel ===");

        System.out.println(
                "cancel 100="
                        + tree.cancel(100));

        System.out.println(
                "cancel 999="
                        + tree.cancel(999));

        printSummary(tree);

        System.out.println("=== cancel root ===");

        System.out.println(
                "cancel 300="
                        + tree.cancel(300));

        printSummary(tree);
    }
}