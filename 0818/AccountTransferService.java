class Account {
    private String id;
    private int balance;

    Account(String id, int balance) {
        this.id = id;
        this.balance = Math.max(0, balance);
    }

    boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }


    void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return id + " balance=" + balance;
    }
}

class TransferService {

    static boolean transfer(
            Account source,
            Account target,
            int amount) {

        if (source == null || target == null) {
            return false;
        }

        if (source == target) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        if (source.getBalance() < amount) {
            return false;
        }

        source.withdraw(amount);
        target.deposit(amount);

        return true;
    }
}

public class AccountTransferService {

    public static void main(String[] args) {

        Account accountA =
                new Account("A001", 1000);

        Account accountB =
                new Account("B001", 500);


        System.out.println("=== 成功轉帳 ===");

        System.out.println(
                "轉帳結果："
                + TransferService.transfer(
                        accountA,
                        accountB,
                        300));

        System.out.println(accountA);
        System.out.println(accountB);



        System.out.println("\n=== 餘額不足 ===");

        System.out.println(
                "轉帳結果："
                + TransferService.transfer(
                        accountA,
                        accountB,
                        1000));

        System.out.println(accountA);
        System.out.println(accountB);


        System.out.println("\n=== 同帳戶轉帳 ===");

        System.out.println(
                "轉帳結果："
                + TransferService.transfer(
                        accountA,
                        accountA,
                        100));

        System.out.println(accountA);


        System.out.println("\n=== null 目標 ===");

        System.out.println(
                "轉帳結果："
                + TransferService.transfer(
                        accountA,
                        null,
                        100));

        System.out.println(accountA);
    }
}