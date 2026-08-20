class Customer {
    private String id;
    private String name;

    // Constructor
    Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String label() {
        return id + " " + name;
    }
}

class OrderItem {
    private String productName;
    private int price;
    private int quantity;

    // Constructor
    OrderItem(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = Math.max(0, price);
        this.quantity = Math.max(0, quantity);
    }

    // 計算單項金額
    int getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return productName
                + " price=" + price
                + " quantity=" + quantity
                + " subtotal=" + getSubtotal();
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    CustomerOrder(String orderId, Customer customer, int capacity) {
        this.orderId = orderId;
        this.customer = customer;

        if (capacity <= 0) {
            capacity = 1;
        }

        this.items = new OrderItem[capacity];
        this.itemCount = 0;
    }

    boolean addItem(OrderItem item) {
        if (item == null || itemCount >= items.length) {
            return false;
        }

        items[itemCount] = item;
        itemCount++;

        return true;
    }

    int getTotal() {
        int total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].getSubtotal();
        }

        return total;
    }

    int getItemCount() {
        return itemCount;
    }

    String summary() {
        return orderId
                + " | Customer: "
                + customer.label()
                + " | Items: "
                + itemCount
                + " | Total: $"
                + getTotal();
    }

    void printOrder() {
        System.out.println(summary());

        for (int i = 0; i < itemCount; i++) {
            System.out.println("  " + items[i]);
        }
    }
}

public class CustomerOrderSystem {

    public static void main(String[] args) {

        Customer customer =
                new Customer("C001", "Amy");

        CustomerOrder order =
                new CustomerOrder("O001", customer, 3);
        OrderItem item1 =
                new OrderItem("Keyboard", 500, 2);

        OrderItem item2 =
                new OrderItem("Mouse", 300, 1);

        OrderItem item3 =
                new OrderItem("USB", 150, 3);
        System.out.println("加入 Keyboard："
                + order.addItem(item1));

        System.out.println("加入 Mouse："
                + order.addItem(item2));

        System.out.println("加入 USB："
                + order.addItem(item3));

        System.out.println("\n訂單資訊：");
        order.printOrder();


        System.out.println("\n品項數量："
                + order.getItemCount());

        System.out.println("訂單總額：$"
                + order.getTotal());
    }
}