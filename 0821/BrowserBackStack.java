import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private final Deque<String> history = new ArrayDeque<>();

    void visit(String page) {
        if (page != null && !page.isBlank()) {
            history.push(page);
        }
    }

    String back() {
        if (history.size() <= 1) {
            return null;
        }

        history.pop();
        return history.peek();
    }

    String current() {
        return history.peek();
    }

    int size() {
        return history.size();
    }

    public static void main(String[] args) {
        BrowserBackStack browser =
                new BrowserBackStack();

        browser.visit("Google");
        System.out.println(
                "目前=" + browser.current());

        browser.visit("GitHub");
        System.out.println(
                "目前=" + browser.current());

        browser.visit("Java");
        System.out.println(
                "目前=" + browser.current());

        System.out.println(
                "返回=" + browser.back());

        System.out.println(
                "目前=" + browser.current());

        System.out.println(
                "返回=" + browser.back());

        System.out.println(
                "目前=" + browser.current());

        browser.visit("YouTube");

        System.out.println(
                "目前=" + browser.current());

        System.out.println(
                "返回=" + browser.back());

        System.out.println(
                "返回=" + browser.back());

        System.out.println(
                "返回=" + browser.back());

        System.out.println(
                "目前=" + browser.current());

        System.out.println(
                "數量=" + browser.size());
    }
}