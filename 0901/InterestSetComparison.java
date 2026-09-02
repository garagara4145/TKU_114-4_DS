import java.util.LinkedHashSet;
import java.util.Set;

public class InterestSetComparison {

    public static Set<String> union(Set<String> first, Set<String> second) {
        Set<String> result = new LinkedHashSet<>(first);
        result.addAll(second);
        return result;
    }

    public static Set<String> intersection(Set<String> first, Set<String> second) {
        Set<String> result = new LinkedHashSet<>(first);
        result.retainAll(second);
        return result;
    }

    public static Set<String> firstOnly(Set<String> first, Set<String> second) {
        Set<String> result = new LinkedHashSet<>(first);
        result.removeAll(second);
        return result;
    }

    public static Set<String> secondOnly(Set<String> first, Set<String> second) {
        Set<String> result = new LinkedHashSet<>(second);
        result.removeAll(first);
        return result;
    }

    public static void main(String[] args) {

        Set<String> first = new LinkedHashSet<>();
        first.add("籃球");
        first.add("電影");
        first.add("音樂");
        first.add("遊戲");

        Set<String> second = new LinkedHashSet<>();
        second.add("音樂");
        second.add("旅遊");
        second.add("電影");
        second.add("攝影");

        System.out.println("First      = " + first);
        System.out.println("Second     = " + second);
        System.out.println();

        System.out.println("Union      = " + union(first, second));
        System.out.println("Intersect  = " + intersection(first, second));
        System.out.println("First Only = " + firstOnly(first, second));
        System.out.println("Second Only= " + secondOnly(first, second));

        System.out.println();
        System.out.println("Original First  = " + first);
        System.out.println("Original Second = " + second);
    }
}