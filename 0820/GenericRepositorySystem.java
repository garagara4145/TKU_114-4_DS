import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private final List<T> data = new ArrayList<>();

    void add(T item) {
        data.add(item);
    }

    T get(int index) {
        if (index < 0 || index >= data.size()) {
            return null;
        }

        return data.get(index);
    }

    boolean remove(T item) {
        return data.remove(item);
    }

    int size() {
        return data.size();
    }

    void printAll() {
        System.out.println(data);
    }
}

class Product {
    private final String id;
    private final String name;
    private final double price;

    Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + name + " price=" + price;
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> names = new Repository<>();

        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        System.out.println("String Repository:");
        names.printAll();
        System.out.println("size=" + names.size());
        System.out.println("get(1)=" + names.get(1));

        names.remove("Ben");

        System.out.println("after remove:");
        names.printAll();


        Repository<Product> products = new Repository<>();

        products.add(
                new Product("P001", "Keyboard", 1200));

        products.add(
                new Product("P002", "Mouse", 500));

        products.add(
                new Product("P003", "Monitor", 3500));

        System.out.println("Product Repository:");
        products.printAll();
        System.out.println("size=" + products.size());
        System.out.println("get(0)=" + products.get(0));

        Product product = products.get(1);
        products.remove(product);

        System.out.println("after remove:");
        products.printAll();
    }
}