public class RecursiveArrayStatistics {

    static int maximum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        return maximum(values, 0);
    }

    static int maximum(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }

        int restMax = maximum(values, index + 1);
        return Math.max(values[index], restMax);
    }

    static int minimum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        return minimum(values, 0);
    }

    static int minimum(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }

        int restMin = minimum(values, index + 1);
        return Math.min(values[index], restMin);
    }

    static int countAbove(int[] values, int target) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        return countAbove(values, target, 0);
    }

    static int countAbove(int[] values, int target, int index) {
        if (index == values.length) {
            return 0;
        }

        int current = values[index] > target ? 1 : 0;
        return current + countAbove(values, target, index + 1);
    }

    public static void main(String[] args) {
        int[] values = {12, 5, 27, 8, 19, 3};

        System.out.println("maximum=" + maximum(values));
        System.out.println("minimum=" + minimum(values));
        System.out.println("countAbove(10)=" + countAbove(values, 10));

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("null array=" + e.getMessage());
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("empty array=" + e.getMessage());
        }
    }
}