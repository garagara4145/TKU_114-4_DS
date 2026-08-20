class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final int balanceAfter;

    WalletTransaction(
            int sequence,
            String type,
            int amount,
            int balanceAfter) {

        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    int getSequence() {
        return sequence;
    }

    String getType() {
        return type;
    }

    int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return sequence
                + " " + type
                + " " + amount
                + " balance=" + balanceAfter;
    }
}

class DigitalWallet {
    private final String walletId;
    private final String owner;
    private int balance;

    private final WalletTransaction[] transactions;
    private int transactionCount;

    DigitalWallet(
            String walletId,
            String owner,
            int historyCapacity) {

        if (walletId == null || walletId.isBlank()) {
            this.walletId = "UNKNOWN";
        } else {
            this.walletId = walletId;
        }

        if (owner == null || owner.isBlank()) {
            this.owner = "Unknown";
        } else {
            this.owner = owner;
        }

        this.balance = 0;

        this.transactions =
                new WalletTransaction[
                        Math.max(1, historyCapacity)];

        this.transactionCount = 0;
    }
    boolean deposit(int amount) {

        if (amount <= 0) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;

        record("DEPOSIT", amount);

        return true;
    }

    boolean pay(int amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        balance -= amount;

        record("PAY", amount);

        return true;
    }

    boolean refund(int amount) {

        if (amount <= 0) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;

        record("REFUND", amount);

        return true;
    }

    WalletTransaction findTransaction(int sequence) {

        for (int i = 0; i < transactionCount; i++) {

            if (transactions[i].getSequence()
                    == sequence) {

                return transactions[i];
            }
        }

        return null;
    }

    int totalByType(String type) {

        if (type == null) {
            return 0;
        }

        int total = 0;

        for (int i = 0; i < transactionCount; i++) {

            if (type.equals(
                    transactions[i].getType())) {

                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    boolean transferTo(
            DigitalWallet target,
            int amount) {

        if (target == null
                || target == this
                || amount <= 0) {

            return false;
        }
        if (amount > balance) {
            return false;
        }

        if (transactionCount >= transactions.length
                || target.transactionCount
                >= target.transactions.length) {

            return false;
        }

        balance -= amount;

        target.balance += amount;

        record("TRANSFER_OUT", amount);

        target.record("TRANSFER_IN", amount);

        return true;
    }

    private void record(
            String type,
            int amount) {

        transactions[transactionCount]
                = new WalletTransaction(
                        transactionCount + 1,
                        type,
                        amount,
                        balance);

        transactionCount++;
    }

    // 輸出完整 statement
    void printStatement() {

        System.out.println(
                walletId
                + " owner=" + owner
                + " balance=" + balance);

        for (int i = 0;
             i < transactionCount;
             i++) {

            System.out.println(
                    transactions[i]);
        }
    }

    int getBalance() {
        return balance;
    }
}

public class WalletHistoryManager {

    public static void main(String[] args) {

        DigitalWallet walletA =
                new DigitalWallet(
                        "W001",
                        "Amy",
                        5);

        DigitalWallet walletB =
                new DigitalWallet(
                        "W002",
                        "Ben",
                        5);



        System.out.println(
                "A 儲值 1000："
                + walletA.deposit(1000));

        System.out.println(
                "A 付款 200："
                + walletA.pay(200));

        System.out.println(
                "A 退款 50："
                + walletA.refund(50));




        System.out.println(
                "\n尋找交易 2：");

        WalletTransaction found =
                walletA.findTransaction(2);

        System.out.println(found);

        System.out.println(
                "\n尋找交易 99：");

        System.out.println(
                walletA.findTransaction(99));



        System.out.println(
                "\nDEPOSIT 總額："
                + walletA.totalByType("DEPOSIT"));

        System.out.println(
                "PAY 總額："
                + walletA.totalByType("PAY"));

        System.out.println(
                "REFUND 總額："
                + walletA.totalByType("REFUND"));



        System.out.println(
                "\nA 轉帳 300 給 B："
                + walletA.transferTo(
                        walletB,
                        300));




        System.out.println(
                "\n=== Wallet A ===");

        walletA.printStatement();

        System.out.println(
                "\n=== Wallet B ===");

        walletB.printStatement();
    }
}