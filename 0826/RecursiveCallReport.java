public class RecursiveCallReport {

    static int sum(int[] data, int index) {
        if (index >= data.length) {
            System.out.println("index=" + index
                    + ", current=null"
                    + ", recursiveResult=0"
                    + ", return=0");
            return 0;
        }

        int current = data[index];

        System.out.println("index=" + index
                + ", current=" + current);

        int recursiveResult = sum(data, index + 1);

        int result = current + recursiveResult;

        System.out.println("index=" + index
                + ", current=" + current
                + ", recursiveResult=" + recursiveResult
                + ", return=" + result);

        return result;
    }

    public static void main(String[] args) {

        System.out.println("--- General Array ---");

        int[] data1 = {10, 20, 30, 40};

        int result1 = sum(data1, 0);

        System.out.println("sum=" + result1);


        System.out.println();


        System.out.println("--- Single Element ---");

        int[] data2 = {50};

        int result2 = sum(data2, 0);

        System.out.println("sum=" + result2);


        System.out.println();


        System.out.println("--- Empty Array ---");

        int[] data3 = {};

        int result3 = sum(data3, 0);

        System.out.println("sum=" + result3);
    }
}