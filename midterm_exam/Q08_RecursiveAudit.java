public class Q08_RecursiveAudit {

    public static int sumValid(int[] data, int index) {
        if (data == null || data.length == 0) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        if (index >= data.length) {
            return 0;
        }

        int value = data[index];

        if (value >= 0 && value <= 100) {
            return value + sumValid(data, index + 1);
        }

        return sumValid(data, index + 1);
    }

    public static int countOccurrences(
            int[] data, int index, int target) {

        if (data == null || data.length == 0) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        if (index >= data.length) {
            return 0;
        }

        int count = data[index] == target ? 1 : 0;

        return count + countOccurrences(
                data, index + 1, target);
    }

    public static boolean isPalindrome(
            String text, int left, int right) {

        if (text == null) {
            return false;
        }

        if (left >= right) {
            return true;
        }

        if (left < 0) {
            left = 0;
        }

        if (right >= text.length()) {
            right = text.length() - 1;
        }

        if (left >= right) {
            return true;
        }

        char leftChar = Character.toLowerCase(text.charAt(left));
        char rightChar = Character.toLowerCase(text.charAt(right));

        if (leftChar != rightChar) {
            return false;
        }

        return isPalindrome(text, left + 1, right - 1);
    }

    public static void main(String[] args) {
        int[] data = {10, -1, 20, 101, 20};

        System.out.println(sumValid(data, 0));
        System.out.println(countOccurrences(data, 0, 20));
        System.out.println(isPalindrome("Level", 0, 4));
    }
}