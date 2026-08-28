class InventoryProduct {
    int id;
    String name;
    int stock;

    InventoryProduct(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = Math.max(0, stock);
    }

    boolean restock(int amount) {
        if (amount <= 0) {
            return false;
        }

        stock += amount;
        return true;
    }

    boolean sell(int amount) {
        if (amount <= 0 || amount > stock) {
            return false;
        }

        stock -= amount;
        return true;
    }

    @Override
    public String toString() {
        return id + " " + name + " stock=" + stock;
    }
}

class InventoryNode {
    InventoryProduct data;
    InventoryNode left;
    InventoryNode right;

    InventoryNode(InventoryProduct data) {
        this.data = data;
    }
}

class ProductInventoryBst {

    private InventoryNode root;

    boolean add(InventoryProduct product) {
        if (product == null) {
            return false;
        }

        if (root == null) {
            root = new InventoryNode(product);
            return true;
        }

        InventoryNode current = root;

        while (true) {
            if (product.id == current.data.id) {
                return false;
            }

            if (product.id < current.data.id) {
                if (current.left == null) {
                    current.left = new InventoryNode(product);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new InventoryNode(product);
                    return true;
                }

                current = current.right;
            }
        }
    }

    InventoryProduct find(int id) {
        InventoryNode current = root;

        while (current != null) {
            if (id == current.data.id) {
                return current.data;
            }

            if (id < current.data.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean restock(int id, int amount) {
        InventoryProduct product = find(id);

        if (product == null) {
            return false;
        }

        return product.restock(amount);
    }

    boolean sell(int id, int amount) {
        InventoryProduct product = find(id);

        if (product == null) {
            return false;
        }

        return product.sell(amount);
    }

    boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }

        root = remove(root, id);
        return true;
    }

    private InventoryNode remove(
            InventoryNode node,
            int id) {

        if (node == null) {
            return null;
        }

        if (id < node.data.id) {
            node.left = remove(node.left, id);
        } else if (id > node.data.id) {
            node.right = remove(node.right, id);
        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            InventoryNode successor =
                    minimumNode(node.right);

            node.data = successor.data;

            node.right =
                    remove(
                            node.right,
                            successor.data.id);
        }

        return node;
    }

    private InventoryNode minimumNode(
            InventoryNode node) {

        InventoryNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(InventoryNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class ProductInventoryBst {

    public static void main(String[] args) {

        ProductInventoryBst tree =
                new ProductInventoryBst();

        System.out.println(
                tree.add(
                        new InventoryProduct(
                                300,
                                "Keyboard",
                                5)));

        System.out.println(
                tree.add(
                        new InventoryProduct(
                                100,
                                "Mouse",
                                8)));

        System.out.println(
                tree.add(
                        new InventoryProduct(
                                500,
                                "Monitor",
                                2)));

        System.out.println(
                tree.add(
                        new InventoryProduct(
                                200,
                                "Hub",
                                4)));

        System.out.println(
                tree.add(
                        new InventoryProduct(
                                100,
                                "Duplicate",
                                10)));

        System.out.println("=== initial ===");
        tree.inorder();

        System.out.println(
                "find 200=" + tree.find(200));

        System.out.println(
                "restock 200 +6="
                + tree.restock(200, 6));

        System.out.println(
                "sell 200 -5="
                + tree.sell(200, 5));

        System.out.println(
                "find 200=" + tree.find(200));

        System.out.println(
                "sell 200 -10="
                + tree.sell(200, 10));

        System.out.println(
                "restock 999 +5="
                + tree.restock(999, 5));

        System.out.println(
                "sell 200 -0="
                + tree.sell(200, 0));

        System.out.println("=== after stock operations ===");
        tree.inorder();

        System.out.println(
                "remove 200=" + tree.remove(200));

        System.out.println(
                "remove 999=" + tree.remove(999));

        System.out.println("=== after remove ===");
        tree.inorder();
    }
}