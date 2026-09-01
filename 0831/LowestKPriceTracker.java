import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    static List<Integer> lowestK(List<Integer> prices, int k) {
        if (prices == null || k <= 0) {
            return List.of();
        }

        PriorityQueue<Integer> heap =
                new PriorityQueue<>(Comparator.reverseOrder());

        for (Integer price : prices) {

            if (price == null || price < 0) {
                continue;
            }

            heap.offer(price);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> result = new ArrayList<>(heap);

        result.sort(Comparator.naturalOrder());

        return result;
    }

    public static void main(String[] args) {

        List<Integer> prices =
                List.of(100, 50, 80, 20, 60, 10, 90);

        System.out.println(
                "lowest3=" + lowestK(prices, 3)
        );

        System.out.println(
                "lowest0=" + lowestK(prices, 0)
        );

        System.out.println(
                "null=" + lowestK(null, 3)
        );
    }
}