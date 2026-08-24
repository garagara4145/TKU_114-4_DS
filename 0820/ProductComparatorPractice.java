import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private final String id;
    private final String name;
    private final double price;
    private final int stock;

    StoreProduct(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id + " " + name
                + " price=" + price
                + " stock=" + stock;
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();

        products.add(
                new StoreProduct("P103", "Keyboard", 1200, 20));

        products.add(
                new StoreProduct("P101", "Mouse", 500, 30));

        products.add(
                new StoreProduct("P105", "Monitor", 500, 20));

        products.add(
                new StoreProduct("P102", "Headset", 800, 30));

        products.add(
                new StoreProduct("P104", "Webcam", 800, 10));

        List<StoreProduct> byId =
                new ArrayList<>(products);

        byId.sort(null);

        System.out.println(
                "by id=" + byId);

        Comparator<StoreProduct> byPrice =
                Comparator.comparingDouble(
                        StoreProduct::getPrice)
                        .thenComparing(
                                StoreProduct::getName);

        List<StoreProduct> priceList =
                new ArrayList<>(products);

        priceList.sort(byPrice);

        System.out.println(
                "by price=" + priceList);

        Comparator<StoreProduct> byStock =
                Comparator.comparingInt(
                        StoreProduct::getStock)
                        .reversed()
                        .thenComparing(
                                StoreProduct::getId);

        List<StoreProduct> stockList =
                new ArrayList<>(products);

        stockList.sort(byStock);

        System.out.println(
                "by stock=" + stockList);

        System.out.println(
                "original=" + products);
    }
}