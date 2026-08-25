import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    private final String recordId;
    private final String name;

    Patient(String recordId, String name) {
        this.recordId = recordId;
        this.name = name;
    }

    String getRecordId() {
        return recordId;
    }

    @Override
    public String toString() {
        return recordId + " " + name;
    }
}

class ClinicQueue {
    private final Deque<Patient> waiting = new ArrayDeque<>();
    private final List<Patient> completed = new ArrayList<>();

    boolean register(Patient patient) {
        if (patient == null) {
            return false;
        }

        waiting.offerLast(patient);
        return true;
    }

    boolean cancel(String recordId) {
        if (recordId == null) {
            return false;
        }

        for (Patient patient : waiting) {
            if (patient.getRecordId().equals(recordId)) {
                waiting.remove(patient);
                return true;
            }
        }

        return false;
    }

    Patient peekNext() {
        return waiting.peekFirst();
    }

    Patient callNext() {
        Patient patient = waiting.pollFirst();

        if (patient != null) {
            completed.add(patient);
        }

        return patient;
    }

    int waitingCount() {
        return waiting.size();
    }

    void printCompleted() {
        System.out.println("完成清單=" + completed);
    }

    void printWaiting() {
        System.out.println("等待隊列=" + waiting);
    }
}

public class ClinicQueueSystem {
    public static void main(String[] args) {
        ClinicQueue clinic = new ClinicQueue();

        System.out.println(
                "空隊列下一位=" + clinic.peekNext());

        System.out.println(
                "掛號=" + clinic.register(
                        new Patient("P001", "Amy")));

        System.out.println(
                "掛號=" + clinic.register(
                        new Patient("P002", "Ben")));

        System.out.println(
                "掛號=" + clinic.register(
                        new Patient("P003", "Cara")));

        System.out.println(
                "掛號=" + clinic.register(
                        new Patient("P004", "David")));

        clinic.printWaiting();

        System.out.println(
                "下一位=" + clinic.peekNext());

        System.out.println(
                "取消 P003=" + clinic.cancel("P003"));

        clinic.printWaiting();

        System.out.println(
                "下一位=" + clinic.peekNext());

        System.out.println(
                "叫號=" + clinic.callNext());

        System.out.println(
                "叫號=" + clinic.callNext());

        clinic.printWaiting();

        System.out.println(
                "取消 P999=" + clinic.cancel("P999"));

        System.out.println(
                "叫號=" + clinic.callNext());

        System.out.println(
                "空隊列叫號=" + clinic.callNext());

        System.out.println(
                "等待人數=" + clinic.waitingCount());

        clinic.printCompleted();
    }
}