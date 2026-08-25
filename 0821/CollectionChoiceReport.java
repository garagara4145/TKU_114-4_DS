import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {
    public static void main(String[] args) {

        List<String> searchHistory = new ArrayList<>();

        searchHistory.add("Java");
        searchHistory.add("Data Structures");
        searchHistory.add("Java");
        searchHistory.add("Queue");

        System.out.println("1. 搜尋記錄");
        System.out.println("介面=List");
        System.out.println("實作=ArrayList");
        System.out.println("結果=" + searchHistory);

        Set<String> memberIds = new HashSet<>();

        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");
        memberIds.add("M003");

        System.out.println();
        System.out.println("2. 不重複會員號碼");
        System.out.println("介面=Set");
        System.out.println("實作=HashSet");
        System.out.println("結果=" + memberIds);

        Map<String, Integer> scores = new HashMap<>();

        scores.put("S001", 85);
        scores.put("S002", 92);
        scores.put("S003", 78);

        System.out.println();
        System.out.println("3. 以學號查詢成績");
        System.out.println("介面=Map");
        System.out.println("實作=HashMap");
        System.out.println("S002成績=" + scores.get("S002"));

        Deque<String> printQueue = new ArrayDeque<>();

        printQueue.offerLast("File-A");
        printQueue.offerLast("File-B");
        printQueue.offerLast("File-C");

        System.out.println();
        System.out.println("4. 依順序處理印刷工作");
        System.out.println("介面=Queue");
        System.out.println("實作=ArrayDeque");
        System.out.println("下一份=" + printQueue.peekFirst());
        System.out.println("處理=" + printQueue.pollFirst());
        System.out.println("處理=" + printQueue.pollFirst());
        System.out.println("剩餘=" + printQueue);

        Deque<String> recentActions = new ArrayDeque<>();

        recentActions.push("登入");
        recentActions.push("搜尋");
        recentActions.push("查看課程");
        recentActions.push("加入購物車");

        System.out.println();
        System.out.println("5. 最近多次操作");
        System.out.println("介面=Deque");
        System.out.println("實作=ArrayDeque");
        System.out.println("最近操作=" + recentActions.peek());
        System.out.println("移除最近操作=" + recentActions.pop());
        System.out.println("現在最近操作=" + recentActions.peek());

        System.out.println();
        System.out.println("集合選擇完成");
    }
}