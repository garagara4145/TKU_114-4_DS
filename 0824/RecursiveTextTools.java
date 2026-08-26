public class RecursiveTextTools {

    static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1)) + text.charAt(0);
    }

    static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        return isPalindrome(cleaned, 0, cleaned.length() - 1);
    }

    static boolean isPalindrome(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindrome(text, left + 1, right - 1);
    }

    static int countCharacter(String text, char target) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        return countCharacter(text, target, 0);
    }

    static int countCharacter(String text, char target, int index) {
        if (index == text.length()) {
            return 0;
        }

        int count = text.charAt(index) == target ? 1 : 0;
        return count + countCharacter(text, target, index + 1);
    }

    public static void main(String[] args) {
        String[] texts = {
            "",
            "A",
            "Level",
            "Hello Java"
        };

        for (String text : texts) {
            System.out.println("text=" + text);
            System.out.println("reverse=" + reverse(text));
            System.out.println("palindrome=" + isPalindrome(text));
        }

        System.out.println("count l in Hello Java="
                + countCharacter("Hello Java", 'l'));
    }
}