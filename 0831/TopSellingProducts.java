import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    record Product(String id, int sales) {
    }

    static List<Product> topK(
            List<Product> products,
            int k) {

        if (products == null || k <= 0) {
            return List.of();
        }

        Map<String, Integer> totals =
                new HashMap<>();

        for (Product product : products) {

            if (product == null
                    || product.id() == null) {
                continue;
            }

            totals.merge(
                    product.id(),
                    product.sales(),
                    Integer::sum
            );
        }

        Comparator<Product> worstFirst =
                Comparator
                        .comparingInt(Product::sales)
                        .thenComparing(
                                Product::id,
                                Comparator.reverseOrder()
                        );

        PriorityQueue<Product> heap =
                new PriorityQueue<>(worstFirst);

        for (Map.Entry<String, Integer> entry
                : totals.entrySet()) {

            Product product =
                    new Product(
                            entry.getKey(),
                            entry.getValue()
                    );

            heap.offer(product);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Product> result =
                new ArrayList<>(heap);

        result.sort(
                Comparator
                        .comparingInt(Product::sales)
                        .reversed()
                        .thenComparing(Product::id)
        );

        return result;
    }

    public static void main(String[] args) {

        List<Product> products = List.of(
                new Product("A", 100),
                new Product("B", 200),
                new Product("C", 150),
                new Product("A", 50),
                new Product("D", 200),
                new Product("E", 80),
                new Product("B", 30)
        );

        List<Product> result =
                topK(products, 3);

        for (Product product : result) {

            System.out.println(
                    product.id()
                    + "|" + product.sales()
            );
        }
    }
}