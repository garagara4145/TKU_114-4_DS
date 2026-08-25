import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    private String currentText = "";

    void edit(String text) {
        undoStack.push(currentText);
        currentText = text;
        redoStack.clear();
    }

    String undo() {
        if (undoStack.isEmpty()) {
            return currentText;
        }

        redoStack.push(currentText);
        currentText = undoStack.pop();
        return currentText;
    }

    String redo() {
        if (redoStack.isEmpty()) {
            return currentText;
        }

        undoStack.push(currentText);
        currentText = redoStack.pop();
        return currentText;
    }

    String current() {
        return currentText;
    }

    void printState() {
        System.out.println(
                "目前=" + currentText
                + " | undo=" + undoStack
                + " | redo=" + redoStack);
    }

    public static void main(String[] args) {
        TextEditorHistory editor =
                new TextEditorHistory();

        editor.printState();

        editor.edit("Hello");
        editor.printState();

        editor.edit("Hello Java");
        editor.printState();

        editor.edit("Hello Java World");
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();

        System.out.println("redo=" + editor.redo());
        editor.printState();

        editor.edit("Hello Data Structures");
        editor.printState();

        System.out.println("redo=" + editor.redo());
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();

        System.out.println("undo=" + editor.undo());
        editor.printState();
    }
}