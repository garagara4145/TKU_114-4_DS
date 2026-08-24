import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Amy");
        names.add("Ben");
        names.add(null);
        names.add("");
        names.add("  ");
        names.add("Amy");
        names.add("Cara");
        names.add("Ben");
        names.add("David");

        System.out.println("清理前=" + names);

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        System.out.println("清理後=" + names);

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : names) {
            if (!uniqueNames.add(name)) {
                duplicateNames.add(name);
            }
        }

        System.out.println("不重複姓名=" + uniqueNames);
        System.out.println("重複姓名=" + duplicateNames);
    }
}