import java.util.*;

public class LoginActivityReport {

    private final Map<String, Integer> loginCounts = new LinkedHashMap<>();
    private final Map<String, Set<String>> ipsByAccount = new LinkedHashMap<>();

    public void recordLogin(String account, String ip) {
        if (account == null || account.isBlank()) {
            throw new IllegalArgumentException("account");
        }

        if (ip == null || ip.isBlank()) {
            throw new IllegalArgumentException("ip");
        }

        account = account.trim();
        ip = ip.trim();

        loginCounts.put(
            account,
            loginCounts.getOrDefault(account, 0) + 1
        );

        ipsByAccount
            .computeIfAbsent(account, key -> new LinkedHashSet<>())
            .add(ip);
    }

    public int loginCount(String account) {
        return loginCounts.getOrDefault(account, 0);
    }

    public int distinctIpCount(String account) {
        Set<String> ips = ipsByAccount.get(account);

        if (ips == null) {
            return 0;
        }

        return ips.size();
    }

    public Set<String> ipsOf(String account) {
        Set<String> ips = ipsByAccount.get(account);

        if (ips == null) {
            return Collections.emptySet();
        }

        return new LinkedHashSet<>(ips);
    }

    public List<String> repeatedLoginReport() {
        List<String> report = new ArrayList<>();

        for (String account : loginCounts.keySet()) {
            int count = loginCounts.get(account);

            if (count > 1) {
                report.add(
                    account
                    + " loginCount=" + count
                    + ", distinctIPs=" + distinctIpCount(account)
                    + ", IPs=" + ipsOf(account)
                );
            }
        }

        return report;
    }

    public void printReport() {
        System.out.println("--- Login Report ---");

        for (String account : loginCounts.keySet()) {
            System.out.println(
                account
                + ": loginCount=" + loginCount(account)
                + ", distinctIPs=" + distinctIpCount(account)
                + ", IPs=" + ipsOf(account)
            );
        }

        System.out.println("--- Repeated Login Report ---");

        for (String line : repeatedLoginReport()) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {

        LoginActivityReport report = new LoginActivityReport();

        report.recordLogin("alice", "192.168.1.10");
        report.recordLogin("alice", "192.168.1.10");
        report.recordLogin("alice", "192.168.1.20");

        report.recordLogin("bob", "10.0.0.5");

        report.recordLogin("charlie", "172.16.0.8");
        report.recordLogin("charlie", "172.16.0.9");
        report.recordLogin("charlie", "172.16.0.8");

        report.printReport();
    }
}