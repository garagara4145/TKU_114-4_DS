import java.util.ArrayList;
import java.util.List;

public class DataStructureDecisionReport {

    enum Requirement {
        INDEX_ACCESS,
        FIFO,
        LIFO,
        SORTED_RANGE,
        NEXT_PRIORITY,
        KEY_LOOKUP,
        RELATION_TRAVERSAL,
        HEAP_INSERT,
        HASH_COLLISION,
        GRAPH_BFS,
        GRAPH_DFS,
        SHORTEST_PATH
    }

    static String choose(Requirement requirement) {

        if (requirement == null) {
            return "UNKNOWN";
        }

        switch (requirement) {
            case INDEX_ACCESS:
                return "ArrayList";

            case FIFO:
                return "ArrayDeque as Queue";

            case LIFO:
                return "ArrayDeque as Stack";

            case SORTED_RANGE:
                return "Balanced BST / TreeMap";

            case NEXT_PRIORITY:
                return "Heap / PriorityQueue";

            case KEY_LOOKUP:
                return "HashMap";

            case RELATION_TRAVERSAL:
                return "Graph adjacency list";

            case HEAP_INSERT:
                return "Heap";

            case HASH_COLLISION:
                return "Hash Table with chaining";

            case GRAPH_BFS:
                return "Graph adjacency list + Queue";

            case GRAPH_DFS:
                return "Graph adjacency list + Stack/Recursion";

            case SHORTEST_PATH:
                return "Graph adjacency list + BFS";

            default:
                return "UNKNOWN";
        }
    }

    static String reason(Requirement requirement) {

        if (requirement == null) {
            return "沒有指定需求";
        }

        switch (requirement) {
            case INDEX_ACCESS:
                return "需要依 index 快速存取";

            case FIFO:
                return "需要先進先出";

            case LIFO:
                return "需要後進先出";

            case SORTED_RANGE:
                return "需要排序與範圍查詢";

            case NEXT_PRIORITY:
                return "需要反覆取得最高優先資料";

            case KEY_LOOKUP:
                return "需要依 key 快速查找";

            case RELATION_TRAVERSAL:
                return "需要保存多對多關係";

            case HEAP_INSERT:
                return "需要有效率地加入 Heap 資料";

            case HASH_COLLISION:
                return "需要處理相同 bucket 的 collision";

            case GRAPH_BFS:
                return "需要使用 Graph 進行廣度優先走訪";

            case GRAPH_DFS:
                return "需要使用 Graph 進行深度優先走訪";

            case SHORTEST_PATH:
                return "無權重 Graph 需要找最少 edge 路徑";

            default:
                return "沒有指定理由";
        }
    }

    static String bigO(Requirement requirement) {

        if (requirement == null) {
            return "N/A";
        }

        switch (requirement) {
            case INDEX_ACCESS:
                return "get O(1)";

            case FIFO:
                return "offer/poll O(1)";

            case LIFO:
                return "push/pop O(1)";

            case SORTED_RANGE:
                return "平衡時 O(log n)，最差 O(n)";

            case NEXT_PRIORITY:
                return "peek O(1)，add/remove O(log n)";

            case KEY_LOOKUP:
                return "平均 O(1)，最差 O(n)";

            case RELATION_TRAVERSAL:
                return "BFS/DFS O(V+E)";

            case HEAP_INSERT:
                return "add O(log n)";

            case HASH_COLLISION:
                return "平均查找 O(1)，最差 O(n)";

            case GRAPH_BFS:
                return "O(V+E)";

            case GRAPH_DFS:
                return "O(V+E)";

            case SHORTEST_PATH:
                return "O(V+E)";

            default:
                return "N/A";
        }
    }

    static void printReport(
            Requirement requirement) {

        System.out.println(
                "需求 " + requirement);

        System.out.println(
                "選擇 " + choose(requirement));

        System.out.println(
                "理由 " + reason(requirement));

        System.out.println(
                " Big-O " + bigO(requirement));

        System.out.println();
    }

    public static void main(String[] args) {

        List<Requirement> requirements =
                new ArrayList<>();

        requirements.add(
                Requirement.INDEX_ACCESS);

        requirements.add(
                Requirement.FIFO);

        requirements.add(
                Requirement.LIFO);

        requirements.add(
                Requirement.SORTED_RANGE);

        requirements.add(
                Requirement.NEXT_PRIORITY);

        requirements.add(
                Requirement.KEY_LOOKUP);

        requirements.add(
                Requirement.RELATION_TRAVERSAL);

        requirements.add(
                Requirement.HEAP_INSERT);

        requirements.add(
                Requirement.HASH_COLLISION);

        requirements.add(
                Requirement.GRAPH_BFS);

        requirements.add(
                Requirement.GRAPH_DFS);

        requirements.add(
                Requirement.SHORTEST_PATH);

        for (Requirement requirement :
                requirements) {

            printReport(requirement);
        }

        System.out.println(
                "null 需求 = "
                        + choose(null));

        System.out.println(
                "null 理由 = "
                        + reason(null));

        System.out.println(
                "null Big-O = "
                        + bigO(null));
    }
}