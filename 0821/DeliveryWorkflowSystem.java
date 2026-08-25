import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Deque;

class Delivery {
    private final String id;
    private final String destination;

    Delivery(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " -> " + destination;
    }
}

class DeliveryWorkflow {
    private final Map<String, Delivery> deliveries = new HashMap<>();
    private final Queue<Delivery> waiting = new ArrayDeque<>();
    private final Deque<Delivery> completed = new ArrayDeque<>();

    boolean add(Delivery delivery) {
        if (delivery == null || deliveries.containsKey(delivery.getId())) {
            return false;
        }

        deliveries.put(delivery.getId(), delivery);
        waiting.offer(delivery);
        return true;
    }

    Delivery process() {
        Delivery delivery = waiting.poll();

        if (delivery != null) {
            completed.push(delivery);
        }

        return delivery;
    }

    Delivery undo() {
        if (completed.isEmpty()) {
            return null;
        }

        Delivery delivery = completed.pop();
        waiting.offer(delivery);
        return delivery;
    }

    Delivery find(String id) {
        return deliveries.get(id);
    }

    int waitingSize() {
        return waiting.size();
    }

    int completedSize() {
        return completed.size();
    }

    void printState() {
        System.out.println("等待=" + waiting);
        System.out.println("完成=" + completed);
    }
}

public class DeliveryWorkflowSystem {
    public static void main(String[] args) {
        DeliveryWorkflow workflow =
                new DeliveryWorkflow();

        System.out.println(
                "新增 D001=" +
                workflow.add(
                        new Delivery("D001", "Taipei")));

        System.out.println(
                "新增 D002=" +
                workflow.add(
                        new Delivery("D002", "New Taipei")));

        System.out.println(
                "新增 D003=" +
                workflow.add(
                        new Delivery("D003", "Taoyuan")));

        System.out.println(
                "重複 D002=" +
                workflow.add(
                        new Delivery("D002", "Kaohsiung")));

        workflow.printState();

        System.out.println(
                "處理=" + workflow.process());

        System.out.println(
                "處理=" + workflow.process());

        workflow.printState();

        System.out.println(
                "查詢 D003=" +
                workflow.find("D003"));

        System.out.println(
                "undo=" + workflow.undo());

        workflow.printState();

        System.out.println(
                "處理=" + workflow.process());

        System.out.println(
                "處理=" + workflow.process());

        workflow.printState();

        System.out.println(
                "undo=" + workflow.undo());

        workflow.printState();

        System.out.println(
                "等待數量=" + workflow.waitingSize());

        System.out.println(
                "完成數量=" + workflow.completedSize());
    }
}