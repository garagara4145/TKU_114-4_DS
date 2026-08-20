import java.util.Arrays;

final class InventorySnapshot {

    private final String warehouseId;
    private final int[] quantities;

    InventorySnapshot(String warehouseId, int[] quantities) {

        if (warehouseId == null || warehouseId.isBlank()) {
            this.warehouseId = "Unknown";
        } else {
            this.warehouseId = warehouseId;
        }

        // Defensive Copy
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(
                    quantities,
                    quantities.length);
        }
    }

    int totalQuantity() {

        int total = 0;

        for (int quantity : quantities) {
            total += quantity;
        }

        return total;
    }

    int outOfStockCount() {

        int count = 0;

        for (int quantity : quantities) {
            if (quantity == 0) {
                count++;
            }
        }

        return count;
    }
    int[] getQuantities() {
        return Arrays.copyOf(
                quantities,
                quantities.length);
    }

    @Override
    public String toString() {
        return warehouseId + " "
                + Arrays.toString(quantities);
    }
}

public class InventorySnapshotPractice {

    public static void main(String[] args) {

        int[] original = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", original);

        System.out.println("原始資料："
                + Arrays.toString(original));

        System.out.println("Snapshot："
                + snapshot);

        System.out.println("總數量："
                + snapshot.totalQuantity());

        System.out.println("缺貨品項："
                + snapshot.outOfStockCount());

        original[0] = 100;

        System.out.println("\n修改 original[0] = 100 後：");
        System.out.println("original："
                + Arrays.toString(original));

        System.out.println("Snapshot："
                + snapshot);

        int[] received = snapshot.getQuantities();

        received[1] = 100;

        System.out.println("\n修改 getter 回傳的陣列後：");
        System.out.println("received："
                + Arrays.toString(received));

        System.out.println("Snapshot："
                + snapshot);
    }
}