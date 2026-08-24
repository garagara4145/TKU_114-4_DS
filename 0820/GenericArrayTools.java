import java.util.Objects;

public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int count = 0;

        for (T value : data) {
            if (Objects.equals(value, target)) {
                count++;
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            return;
        }

        if (first < 0 || first >= data.length
                || second < 0 || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] names = {
                "Amy", "Ben", "Amy", "Cara"
        };

        Integer[] scores = {
                80, 90, 80, 70
        };

        System.out.println(
                "Amy 次數="
                + countMatches(names, "Amy"));

        System.out.println(
                "80 次數="
                + countMatches(scores, 80));

        System.out.println(
                "names 最後一筆="
                + last(names));

        System.out.println(
                "scores 最後一筆="
                + last(scores));

        swap(names, 0, 3);

        System.out.println(
                "交換後 names="
                + java.util.Arrays.toString(names));

        swap(scores, 1, 2);

        System.out.println(
                "交換後 scores="
                + java.util.Arrays.toString(scores));

        System.out.println(
                "null array="
                + last(null));

        String[] empty = {};

        System.out.println(
                "empty array="
                + last(empty));

        swap(names, -1, 2);
        swap(names, 0, 100);

        System.out.println(
                "不合法 index 後 names="
                + java.util.Arrays.toString(names));
    }
}