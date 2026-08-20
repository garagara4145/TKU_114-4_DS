class Equipment {
    private String id;
    private String name;
    private int availableCount;

    Equipment(String id, String name, int availableCount) {
        if (id == null || id.isBlank()) {
            this.id = "Unknown";
        } else {
            this.id = id;
        }

        if (name == null || name.isBlank()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }

        if (availableCount < 0) {
            this.availableCount = 0;
        } else {
            this.availableCount = availableCount;
        }
    }
    boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }

        return false;
    }

    void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }
    @Override
    public String toString() {
        return id + " " + name + " available=" + availableCount;
    }
}

public class EquipmentInventory {

    public static void main(String[] args) {

        Equipment equipment1 =
                new Equipment("E001", "Laptop", 2);

        Equipment equipment2 =
                new Equipment("E002", "Projector", 0);

        System.out.println("初始設備：");
        System.out.println(equipment1);
        System.out.println(equipment2);
        System.out.println("\n借用 Laptop："
                + equipment1.borrowOne());

        System.out.println("再次借用 Laptop："
                + equipment1.borrowOne());
        System.out.println("第三次借用 Laptop："
                + equipment1.borrowOne());

        System.out.println("借用 Projector："
                + equipment2.borrowOne());
        equipment1.returnItems(2);

        System.out.println("\n歸還 2 台 Laptop 後：");
        System.out.println(equipment1);
    }
}