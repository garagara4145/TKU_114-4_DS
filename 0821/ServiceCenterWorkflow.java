import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    private final String id;
    private final String description;

    ServiceTicket(String id, String description) {
        this.id = id;
        this.description = description;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + description;
    }
}

class ServiceCenter {
    private final Map<String, ServiceTicket> tickets = new HashMap<>();
    private final Deque<ServiceTicket> waiting = new ArrayDeque<>();
    private final Deque<ServiceTicket> completed = new ArrayDeque<>();
    private final Set<String> ticketIds = new HashSet<>();

    boolean createTicket(ServiceTicket ticket) {
        if (ticket == null || ticket.getId() == null
                || !ticketIds.add(ticket.getId())) {
            return false;
        }

        tickets.put(ticket.getId(), ticket);
        waiting.offerLast(ticket);
        return true;
    }

    ServiceTicket processNext() {
        ServiceTicket ticket = waiting.pollFirst();

        if (ticket != null) {
            completed.push(ticket);
        }

        return ticket;
    }

    boolean cancelWaiting(String id) {
        if (id == null) {
            return false;
        }

        for (ServiceTicket ticket : waiting) {
            if (id.equals(ticket.getId())) {
                waiting.remove(ticket);
                tickets.remove(id);
                ticketIds.remove(id);
                return true;
            }
        }

        return false;
    }

    ServiceTicket undoLastCompletion() {
        if (completed.isEmpty()) {
            return null;
        }

        ServiceTicket ticket = completed.pop();
        waiting.offerFirst(ticket);

        return ticket;
    }

    ServiceTicket findById(String id) {
        return tickets.get(id);
    }

    void printSummary() {
        System.out.println("等待=" + waiting);
        System.out.println("完成=" + completed);
        System.out.println("總數=" + tickets.size());
        System.out.println("等待數=" + waiting.size());
        System.out.println("完成數=" + completed.size());
    }
}

public class ServiceCenterWorkflow {
    public static void main(String[] args) {
        ServiceCenter center = new ServiceCenter();

        System.out.println(
                "建立 T001=" +
                center.createTicket(
                        new ServiceTicket("T001", "網路問題")));

        System.out.println(
                "建立 T002=" +
                center.createTicket(
                        new ServiceTicket("T002", "帳號問題")));

        System.out.println(
                "建立 T003=" +
                center.createTicket(
                        new ServiceTicket("T003", "付款問題")));

        System.out.println(
                "重複 T002=" +
                center.createTicket(
                        new ServiceTicket("T002", "重複工單")));

        center.printSummary();

        System.out.println(
                "取消 T002=" +
                center.cancelWaiting("T002"));

        System.out.println(
                "取消 T999=" +
                center.cancelWaiting("T999"));

        center.printSummary();

        System.out.println(
                "處理=" + center.processNext());

        System.out.println(
                "處理=" + center.processNext());

        center.printSummary();

        System.out.println(
                "undo=" + center.undoLastCompletion());

        center.printSummary();

        System.out.println(
                "undo=" + center.undoLastCompletion());

        center.printSummary();

        System.out.println(
                "空完成 undo=" +
                center.undoLastCompletion());

        System.out.println(
                "查詢 T001=" +
                center.findById("T001"));

        System.out.println(
                "查詢 T999=" +
                center.findById("T999"));
    }
}