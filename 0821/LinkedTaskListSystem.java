class Task {
    private final String id;
    private final String name;

    Task(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    boolean addFirst(Task task) {
        if (task == null || findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);
        node.next = head;
        head = node;
        size++;

        return true;
    }

    boolean addLast(Task task) {
        if (task == null || findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);

        if (head == null) {
            head = node;
        } else {
            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }

        size++;
        return true;
    }

    Task findById(String id) {
        if (id == null) {
            return null;
        }

        TaskNode current = head;

        while (current != null) {
            if (id.equals(current.task.getId())) {
                return current.task;
            }

            current = current.next;
        }

        return null;
    }

    boolean removeById(String id) {
        if (id == null || head == null) {
            return false;
        }

        if (id.equals(head.task.getId())) {
            head = head.next;
            size--;
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (id.equals(current.next.task.getId())) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    boolean insertAfter(String existingId, Task task) {
        if (existingId == null
                || task == null
                || findById(task.getId()) != null) {
            return false;
        }

        TaskNode current = head;

        while (current != null) {
            if (existingId.equals(current.task.getId())) {
                TaskNode node = new TaskNode(task);
                node.next = current.next;
                current.next = node;
                size++;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    int size() {
        return size;
    }

    void printAll() {
        TaskNode current = head;

        System.out.print("[");

        while (current != null) {
            System.out.print(current.task);

            if (current.next != null) {
                System.out.print(", ");
            }

            current = current.next;
        }

        System.out.println("]");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        list.printAll();

        System.out.println(
                "addFirst T001=" +
                list.addFirst(
                        new Task("T001", "Study")));

        System.out.println(
                "addLast T002=" +
                list.addLast(
                        new Task("T002", "Homework")));

        System.out.println(
                "addLast T003=" +
                list.addLast(
                        new Task("T003", "Project")));

        list.printAll();

        System.out.println(
                "duplicate T002=" +
                list.addLast(
                        new Task("T002", "Duplicate")));

        System.out.println(
                "find T002=" +
                list.findById("T002"));

        System.out.println(
                "insert after T001=" +
                list.insertAfter(
                        "T001",
                        new Task("T004", "Reading")));

        list.printAll();

        System.out.println(
                "remove head T001=" +
                list.removeById("T001"));

        list.printAll();

        System.out.println(
                "remove middle T003=" +
                list.removeById("T003"));

        list.printAll();

        System.out.println(
                "remove tail T002=" +
                list.removeById("T002"));

        list.printAll();

        System.out.println(
                "find T999=" +
                list.findById("T999"));

        System.out.println(
                "remove T999=" +
                list.removeById("T999"));

        System.out.println(
                "size=" + list.size());
    }
}