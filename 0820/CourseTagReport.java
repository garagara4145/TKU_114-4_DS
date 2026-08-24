import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] tags = {
                "Java",
                "Database",
                "Java",
                "Algorithm",
                "Database",
                "Java",
                "Web"
        };

        List<String> originalOrder = new ArrayList<>();
        Set<String> uniqueTags = new HashSet<>();
        Map<String, Integer> tagCounts = new HashMap<>();

        for (String tag : tags) {
            originalOrder.add(tag);
            uniqueTags.add(tag);
            tagCounts.put(
                    tag,
                    tagCounts.getOrDefault(tag, 0) + 1
            );
        }

        System.out.println(
                "原始順序=" + originalOrder);

        System.out.println(
                "不重複標籤=" + uniqueTags);

        System.out.println(
                "標籤次數=" + tagCounts);

        System.out.println(
                "Java 次數=" + tagCounts.get("Java"));

        System.out.println(
                "Database 次數="
                + tagCounts.get("Database"));

        System.out.println(
                "List 用途：保存原始順序");

        System.out.println(
                "Set 用途：保存不重複標籤");

        System.out.println(
                "Map 用途：統計每個標籤出現次數");
    }
}