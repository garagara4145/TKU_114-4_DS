import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Number value : values) {
            total += value.doubleValue();
        }

        return total / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }

        double maximum = values.get(0).doubleValue();

        for (Number value : values) {
            double number = value.doubleValue();

            if (number > maximum) {
                maximum = number;
            }
        }

        return maximum;
    }

    static void addRange(
            List<? super Integer> target,
            int start,
            int end) {

        if (target == null || start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers =
                new ArrayList<>();

        integers.add(80);
        integers.add(90);
        integers.add(70);

        List<Double> doubles =
                new ArrayList<>();

        doubles.add(80.5);
        doubles.add(90.5);
        doubles.add(70.5);

        List<Integer> emptyList =
                new ArrayList<>();

        List<Number> numbers =
                new ArrayList<>();

        addRange(numbers, 1, 5);

        System.out.println(
                "Integer average="
                + average(integers));

        System.out.println(
                "Integer maximum="
                + maximum(integers));

        System.out.println(
                "Double average="
                + average(doubles));

        System.out.println(
                "Double maximum="
                + maximum(doubles));

        System.out.println(
                "Empty average="
                + average(emptyList));

        System.out.println(
                "Empty maximum="
                + maximum(emptyList));

        System.out.println(
                "Range=" + numbers);

        addRange(numbers, 5, 1);

        System.out.println(
                "start > end=" + numbers);
    }
}