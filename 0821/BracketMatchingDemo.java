import java.util.ArrayDeque;
import java.util.Deque;

public class BracketMatchingDemo {
    static boolean isBalanced(String expression) {
        return firstErrorIndex(expression) == -1;
    }

    static int firstErrorIndex(String expression) {
        if (expression == null) {
            return 0;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);

            if (symbol == '(' || symbol == '[' || symbol == '{') {
                stack.push(symbol);
            } else if (symbol == ')' || symbol == ']' || symbol == '}') {
                if (stack.isEmpty()) {
                    return i;
                }

                if (!matches(stack.pop(), symbol)) {
                    return i;
                }
            }
        }

        if (!stack.isEmpty()) {
            return expression.length();
        }

        return -1;
    }

    static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String[] expressions = {
                "{[()]}",
                "([)]",
                "(()",
                "a + (b * c)",
                "",
                "]abc",
                "{[}"
        };

        for (String expression : expressions) {
            System.out.println(
                    expression
                    + " -> "
                    + isBalanced(expression)
                    + ", errorIndex="
                    + firstErrorIndex(expression));
        }
    }
}