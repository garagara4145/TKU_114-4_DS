import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char open = stack.pop();

                if (!matches(open, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null || command.trim().isEmpty()) {
                continue;
            }

            String trimmed = command.trim();
            String[] parts = trimmed.split("\\s+");

            if (parts.length != 2
                    && !trimmed.equals("PROCESS")) {
                continue;
            }

            if (parts.length == 2) {
                String type = parts[0];
                String id = parts[1];

                if (id.isEmpty()) {
                    continue;
                }

                if (type.equals("NORMAL")) {
                    normalQueue.offer(id);
                } else if (type.equals("URGENT")) {
                    urgentQueue.offer(id);
                }

            } else if (trimmed.equals("PROCESS")) {
                String processed;

                if (!urgentQueue.isEmpty()) {
                    processed = takeUrgentCheckpoint(urgentQueue);
                } else if (!normalQueue.isEmpty()) {
                    processed = normalQueue.poll();
                } else {
                    processed = "EMPTY";
                }

                result.add(processed);
            }
        }

        return result;
    }

    private static String takeUrgentCheckpoint(
            Deque<String> urgentQueue) {
        return urgentQueue.poll();
    }

    public static void main(String[] args) {
        String[] commands = {
            "NORMAL N1",
            "URGENT U1",
            "NORMAL N2",
            "PROCESS",
            "PROCESS",
            "PROCESS"
        };

        System.out.println(
                Q07_RequestPipeline.isBalanced("a{b[c](d)}"));

        System.out.println(
                Q07_RequestPipeline.isBalanced("([)]"));

        System.out.println(
                Q07_RequestPipeline.process(commands));
    }
}