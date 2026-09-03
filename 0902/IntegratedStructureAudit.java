import java.util.ArrayList;
import java.util.List;

public class IntegratedStructureAudit {

    enum Structure {
        LIST,
        QUEUE,
        BST,
        HEAP,
        HASH_TABLE,
        GRAPH
    }

    static class AuditResult {
        String requirement;
        Structure choice;
        String reason;
        String bigO;
        boolean reasonable;

        AuditResult(
                String requirement,
                Structure choice,
                String reason,
                String bigO,
                boolean reasonable) {

            this.requirement = requirement;
            this.choice = choice;
            this.reason = reason;
            this.bigO = bigO;
            this.reasonable = reasonable;
        }

        @Override
        public String toString() {
            return "需求 = " + requirement
                    + " | 選擇 = " + choice
                    + " | 理由 = " + reason
                    + " | Big-O = " + bigO
                    + " | 判斷 = "
                    + (reasonable ? "合理" : "不合理");
        }
    }

    public static AuditResult audit(
            String requirement,
            Structure actual) {

        if (requirement == null
                || actual == null) {

            return new AuditResult(
                    "無效需求",
                    actual,
                    "輸入無效",
                    "N/A",
                    false);
        }

        String key =
                requirement.trim()
                        .toUpperCase();

        switch (key) {

            case "INDEX":
                return check(
                        requirement,
                        actual,
                        Structure.LIST,
                        "需要依索引快速取得元素",
                        "O(1)");

            case "FIFO":
                return check(
                        requirement,
                        actual,
                        Structure.QUEUE,
                        "需要先進先出",
                        "O(1)");

            case "LIFO":
                return check(
                        requirement,
                        actual,
                        Structure.LIST,
                        "需要後進先出，可使用 List 作為 Stack",
                        "O(1)");

            case "SORTED_RANGE":
                return check(
                        requirement,
                        actual,
                        Structure.BST,
                        "需要維持排序並進行範圍查詢",
                        "O(log n) 平衡時");

            case "NEXT_PRIORITY":
                return check(
                        requirement,
                        actual,
                        Structure.HEAP,
                        "需要快速取得最高優先權資料",
                        "peek O(1)，remove O(log n)");

            case "KEY_LOOKUP":
                return check(
                        requirement,
                        actual,
                        Structure.HASH_TABLE,
                        "需要依 Key 快速查詢",
                        "平均 O(1)");

            case "RELATION":
                return check(
                        requirement,
                        actual,
                        Structure.GRAPH,
                        "需要保存節點之間的關係",
                        "依表示法而定");

            case "HEAP_INSERT":
                return check(
                        requirement,
                        actual,
                        Structure.HEAP,
                        "需要持續加入並維持 Heap",
                        "O(log n)");

            case "HASH_COLLISION":
                return check(
                        requirement,
                        actual,
                        Structure.HASH_TABLE,
                        "需要處理 Key collision",
                        "平均 O(1)");

            case "GRAPH_BFS":
                return check(
                        requirement,
                        actual,
                        Structure.GRAPH,
                        "需要使用 Graph 進行 BFS 關係走訪",
                        "O(V + E)");

            case "GRAPH_DFS":
                return check(
                        requirement,
                        actual,
                        Structure.GRAPH,
                        "需要使用 Graph 進行 DFS 關係走訪",
                        "O(V + E)");

            case "SHORTEST_PATH":
                return check(
                        requirement,
                        actual,
                        Structure.GRAPH,
                        "無法檢視",
                        "O(V + E)");

            default:
                return new AuditResult(
                        requirement,
                        actual,
                        "未知需求",
                        "N/A",
                        false);
        }
    }

    private static AuditResult check(
            String requirement,
            Structure actual,
            Structure expected,
            String reason,
            String bigO) {

        return new AuditResult(
                requirement,
                actual,
                reason,
                bigO,
                actual == expected);
    }

    public static List<AuditResult> runAudit() {

        List<AuditResult> results =
                new ArrayList<>();

        results.add(
                audit(
                        "INDEX",
                        Structure.LIST));

        results.add(
                audit(
                        "FIFO",
                        Structure.QUEUE));

        results.add(
                audit(
                        "LIFO",
                        Structure.LIST));

        results.add(
                audit(
                        "SORTED_RANGE",
                        Structure.BST));

        results.add(
                audit(
                        "NEXT_PRIORITY",
                        Structure.HEAP));

        results.add(
                audit(
                        "KEY_LOOKUP",
                        Structure.HASH_TABLE));

        results.add(
                audit(
                        "RELATION",
                        Structure.GRAPH));

        results.add(
                audit(
                        "HEAP_INSERT",
                        Structure.HEAP));

        results.add(
                audit(
                        "HASH_COLLISION",
                        Structure.HASH_TABLE));

        results.add(
                audit(
                        "GRAPH_BFS",
                        Structure.GRAPH));

        results.add(
                audit(
                        "GRAPH_DFS",
                        Structure.GRAPH));

        results.add(
                audit(
                        "SHORTEST_PATH",
                        Structure.GRAPH));

        return results;
    }

    public static void printReport(
            List<AuditResult> results) {

        if (results == null
                || results.isEmpty()) {

            System.out.println("沒有資料");
            return;
        }

        int correct = 0;

        System.out.println(
                "診斷");

        for (int i = 0;
                i < results.size();
                i++) {

            AuditResult result =
                    results.get(i);

            System.out.println(
                    "測試 " + (i + 1));

            System.out.println(
                    result);

            if (result.reasonable) {
                correct++;
            }
        }

      

        System.out.println(
                "總數"
                        + results.size());

        System.out.println(
                "合理 "
                        + correct);

        System.out.println(
                "不合理 "
                        + (results.size() - correct));
    }

    public static void main(String[] args) {

        List<AuditResult> results =
                runAudit();

        printReport(results);

        System.out.println();

        System.out.println(
                "錯誤");

        AuditResult test1 =
                audit(
                        "FIFO",
                        Structure.BST);

        System.out.println(test1);

        AuditResult test2 =
                audit(
                        "NEXT_PRIORITY",
                        Structure.QUEUE);

        System.out.println(test2);

        AuditResult test3 =
                audit(
                        "KEY_LOOKUP",
                        Structure.GRAPH);

        System.out.println(test3);

        System.out.println();

        System.out.println(
                "無效");

        System.out.println(
                audit(
                        null,
                        Structure.LIST));

        System.out.println(
                audit(
                        "UNKNOWN",
                        Structure.LIST));
    }
}