import java.util.ArrayList;
import java.util.List;

public class OrderManagementBst {

    static class Order {
        final int orderId;
        final String customer;
        final double amount;
        String status;

        Order(
                int orderId,
                String customer,
                double amount,
                String status) {

            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        @Override
        public String toString() {
            return orderId
                    + " "
                    + customer
                    + " amount="
                    + amount
                    + " status="
                    + status;
        }
    }

    static class Node {
        Order data;
        Node left;
        Node right;

        Node(Order data) {
            this.data = data;
        }
    }

    static class OrderBst {

        private Node root;

        boolean add(Order order) {

            if (order == null) {
                return false;
            }

            if (order.customer == null ||
                    order.customer.isBlank()) {
                return false;
            }

            if (order.amount < 0) {
                return false;
            }

            if (order.status == null ||
                    order.status.isBlank()) {
                return false;
            }

            if (root == null) {
                root = new Node(order);
                return true;
            }

            Node current = root;

            while (true) {

                if (order.orderId ==
                        current.data.orderId) {
                    return false;
                }

                if (order.orderId <
                        current.data.orderId) {

                    if (current.left == null) {
                        current.left =
                                new Node(order);
                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right =
                                new Node(order);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Order find(int orderId) {

            Node current = root;

            while (current != null) {

                if (orderId ==
                        current.data.orderId) {
                    return current.data;
                }

                if (orderId <
                        current.data.orderId) {

                    current = current.left;

                } else {

                    current = current.right;
                }
            }

            return null;
        }

        boolean updateStatus(
                int orderId,
                String status) {

            if (status == null ||
                    status.isBlank()) {
                return false;
            }

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            order.status = status;

            return true;
        }

        boolean cancel(int orderId) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if ("CANCELLED".equals(
                    order.status)) {
                return false;
            }

            order.status = "CANCELLED";

            return true;
        }

        boolean remove(int orderId) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if (!"CANCELLED".equals(
                    order.status)) {
                return false;
            }

            root = remove(root, orderId);

            return true;
        }

        private Node remove(
                Node node,
                int orderId) {

            if (orderId <
                    node.data.orderId) {

                node.left =
                        remove(
                                node.left,
                                orderId
                        );

            } else if (orderId >
                    node.data.orderId) {

                node.right =
                        remove(
                                node.right,
                                orderId
                        );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        minimum(node.right);

                node.data = successor.data;

                node.right =
                        remove(
                                node.right,
                                successor.data.orderId
                        );
            }

            return node;
        }

        private Node minimum(Node node) {

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        List<Order> idRange(
                int low,
                int high) {

            List<Order> result =
                    new ArrayList<>();

            if (low > high) {
                return result;
            }

            idRange(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        private void idRange(
                Node node,
                int low,
                int high,
                List<Order> result) {

            if (node == null) {
                return;
            }

            if (low < node.data.orderId) {

                idRange(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low <= node.data.orderId &&
                    node.data.orderId <= high) {

                result.add(node.data);
            }

            if (node.data.orderId < high) {

                idRange(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        double totalAmount() {

            return totalAmount(root);
        }

        private double totalAmount(Node node) {

            if (node == null) {
                return 0;
            }

            return node.data.amount
                    + totalAmount(node.left)
                    + totalAmount(node.right);
        }

        List<Order> inorder() {

            List<Order> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Order> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.data);

            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        OrderBst orders =
                new OrderBst();

        System.out.println(
                "add=" +
                orders.add(
                        new Order(
                                1001,
                                "Alice",
                                500,
                                "PENDING"
                        )
                )
        );

        System.out.println(
                "add=" +
                orders.add(
                        new Order(
                                1003,
                                "Bob",
                                1200,
                                "PAID"
                        )
                )
        );

        System.out.println(
                "add=" +
                orders.add(
                        new Order(
                                1005,
                                "Carol",
                                800,
                                "PENDING"
                        )
                )
        );

        System.out.println(
                "add=" +
                orders.add(
                        new Order(
                                1002,
                                "David",
                                300,
                                "PAID"
                        )
                )
        );

        System.out.println(
                "add=" +
                orders.add(
                        new Order(
                                1004,
                                "Eva",
                                700,
                                "PENDING"
                        )
                )
        );

        System.out.println(
                "duplicate=" +
                orders.add(
                        new Order(
                                1001,
                                "Other",
                                999,
                                "PAID"
                        )
                )
        );

        System.out.println(
                "negativeAmount=" +
                orders.add(
                        new Order(
                                1006,
                                "Frank",
                                -100,
                                "PENDING"
                        )
                )
        );

        System.out.println(
                "find=" +
                orders.find(1003)
        );

        System.out.println(
                "updateStatus=" +
                orders.updateStatus(
                        1003,
                        "SHIPPED"
                )
        );

        System.out.println(
                "cancel=" +
                orders.cancel(1005)
        );

        System.out.println(
                "removeNotCancelled=" +
                orders.remove(1001)
        );

        System.out.println(
                "removeCancelled=" +
                orders.remove(1005)
        );

        System.out.println(
                "missingRemove=" +
                orders.remove(9999)
        );

        System.out.println();

        System.out.println(
                "--- ID Range [1001, 1004] ---"
        );

        for (Order order :
                orders.idRange(
                        1001,
                        1004)) {

            System.out.println(order);
        }

        System.out.println();

        System.out.println(
                "--- Inorder Report ---"
        );

        for (Order order :
                orders.inorder()) {

            System.out.println(order);
        }

        System.out.println();

        System.out.println(
                "total amount="
                + orders.totalAmount()
        );
    }
}