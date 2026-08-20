class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    // Constructor
    DigitalWallet(String walletId, String owner) {
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
        this.transactionCount = 0;
    }

    // 儲值
    boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;

        return true;
    }

    // 付款
    boolean pay(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        transactionCount++;

        return true;
    }

    // 退款
    boolean refund(int amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;

        return true;
    }

    // 取得餘額
    int getBalance() {
        return balance;
    }

    // 取得交易次數
    int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return walletId
                + " owner=" + owner
                + " balance=" + balance
                + " transactions=" + transactionCount;
    }
}

public class DigitalWalletSystem {

    public static void main(String[] args) {

        DigitalWallet wallet =
                new DigitalWallet("W001", "Amy");

        // 1. 正常儲值
        System.out.println("儲值 1000："
                + wallet.deposit(1000));

        System.out.println(wallet);

        // 2. 正常付款
        System.out.println("付款 250："
                + wallet.pay(250));

        System.out.println(wallet);

        // 3. 餘額不足
        System.out.println("付款 900："
                + wallet.pay(900));

        System.out.println(wallet);

        // 4. 負數金額
        System.out.println("儲值 -100："
                + wallet.deposit(-100));

        System.out.println(wallet);

        // 5. 正常退款
        System.out.println("退款 50："
                + wallet.refund(50));

        System.out.println(wallet);
    }
}