import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> heap) {

        if (heap == null) {
            return false;
        }

        for (int parent = 0; parent < heap.size(); parent++) {

            int left = parent * 2 + 1;
            int right = parent * 2 + 2;

            if (left < heap.size()
                    && heap.get(parent) > heap.get(left)) {
                return false;
            }

            if (right < heap.size()
                    && heap.get(parent) > heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isMaxHeap(List<Integer> heap) {

        if (heap == null) {
            return false;
        }

        for (int parent = 0; parent < heap.size(); parent++) {

            int left = parent * 2 + 1;
            int right = parent * 2 + 2;

            if (left < heap.size()
                    && heap.get(parent) < heap.get(left)) {
                return false;
            }

            if (right < heap.size()
                    && heap.get(parent) < heap.get(right)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(
                "min1=" +
                isMinHeap(List.of(10, 20, 15, 30, 40))
        );

        System.out.println(
                "min2=" +
                isMinHeap(List.of(10, 5, 20))
        );

        System.out.println(
                "max1=" +
                isMaxHeap(List.of(50, 30, 40, 10, 20))
        );

        System.out.println(
                "max2=" +
                isMaxHeap(List.of(50, 60, 40))
        );

        System.out.println(
                "null=" +
                isMinHeap(null)
        );

        System.out.println(
                "empty=" +
                isMinHeap(List.of())
        );

        System.out.println(
                "single=" +
                isMinHeap(List.of(10))
        );
    }
}