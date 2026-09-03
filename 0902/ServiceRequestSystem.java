import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {

    public record Request(
            String id,
            String description,
            int priority,
            long sequence) {
    }

    private Map<String, Request> requests;
    private PriorityQueue<Request> queue;

    public ServiceRequestSystem() {

        requests = new HashMap<>();

        queue = new PriorityQueue<>(
                Comparator.comparingInt(Request::priority)
                        .thenComparingLong(Request::sequence)
                        .thenComparing(Request::id));
    }

    public boolean submit(Request request) {

        if (request == null
                || request.id() == null
                || request.description() == null) {
            return false;
        }

        String id = request.id().trim();
        String description =
                request.description().trim();

        if (id.isEmpty()
                || description.isEmpty()) {
            return false;
        }

        if (requests.containsKey(id)) {
            return false;
        }

        Request newRequest =
                new Request(
                        id,
                        description,
                        request.priority(),
                        request.sequence());

        requests.put(id, newRequest);
        queue.offer(newRequest);

        return true;
    }

    public Request find(String id) {

        if (id == null) {
            return null;
        }

        return requests.get(id.trim());
    }

    public Request next() {

        while (!queue.isEmpty()) {

            Request request =
                    queue.poll();

            if (requests.containsKey(request.id())) {
                requests.remove(request.id());
                return request;
            }
        }

        return null;
    }

    public boolean cancel(String id) {

        if (id == null) {
            return false;
        }

        id = id.trim();

        Request request =
                requests.remove(id);

        if (request == null) {
            return false;
        }

        queue.remove(request);

        return true;
    }

    public int pendingCount() {
        return requests.size();
    }

    public List<Request> pendingRequests() {

        List<Request> result =
                new ArrayList<>();

        for (Request request : requests.values()) {
            result.add(request);
        }

        result.sort(
                Comparator.comparingInt(Request::priority)
                        .thenComparingLong(Request::sequence)
                        .thenComparing(Request::id));

        return result;
    }

    public static void main(String[] args) {

        ServiceRequestSystem system =
                new ServiceRequestSystem();

        System.out.println(
                "提交請求 A = "
                        + system.submit(
                                new Request(
                                        "A",
                                        "印表機故障",
                                        2,
                                        1)));

        System.out.println(
                "提交請求 B = "
                        + system.submit(
                                new Request(
                                        "B",
                                        "網路故障",
                                        1,
                                        2)));

        System.out.println(
                "提交請求 C = "
                        + system.submit(
                                new Request(
                                        "C",
                                        "電腦故障",
                                        1,
                                        3)));

        System.out.println(
                "提交請求 D = "
                        + system.submit(
                                new Request(
                                        "D",
                                        "門禁故障",
                                        3,
                                        4)));

        System.out.println(
                "重複提交 A = "
                        + system.submit(
                                new Request(
                                        "A",
                                        "其他問題",
                                        1,
                                        5)));

        System.out.println(
                "查詢 A = "
                        + system.find("A"));

        System.out.println(
                "查詢 Z = "
                        + system.find("Z"));

        System.out.println(
                "目前待處理 = "
                        + system.pendingCount());

        System.out.println(
                "取消 D = "
                        + system.cancel("D"));

        System.out.println(
                "取消後待處理 = "
                        + system.pendingCount());

        System.out.println(
                "取消 Z = "
                        + system.cancel("Z"));

        System.out.println(
                "下一個請求 = "
                        + system.next());

        System.out.println(
                "下一個請求 = "
                        + system.next());

        System.out.println(
                "下一個請求 = "
                        + system.next());

        System.out.println(
                "空 Queue 下一個 = "
                        + system.next());

        System.out.println(
                "最後待處理 = "
                        + system.pendingCount());

        System.out.println();

        System.out.println(
                "空白 ID = "
                        + system.submit(
                                new Request(
                                        "   ",
                                        "測試",
                                        1,
                                        10)));

        System.out.println(
                "null Request = "
                        + system.submit(null));

        System.out.println(
                "null ID 查詢 = "
                        + system.find(null));

        System.out.println(
                "null ID 取消 = "
                        + system.cancel(null));
    }
}