import java.util.List;

public class Q03_EmployeePayroll {

    public static abstract class Employee {
        private final String id;
        private final String name;

        protected Employee(String id, String name) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract int monthlyPay();

        public String summary() {
            return id + "|" + name + "|" + monthlyPay();
        }
    }

    public static class SalariedEmployee extends Employee {
        private final int salary;

        public SalariedEmployee(String id, String name, int salary) {
            super(id, name);
            this.salary = Math.max(0, salary);
        }

        @Override
        public int monthlyPay() {
            return salary;
        }
    }

    public static class HourlyEmployee extends Employee {
        private final int hours;
        private final int hourlyRate;

        public HourlyEmployee(
                String id,
                String name,
                int hours,
                int hourlyRate) {
            super(id, name);
            this.hours = Math.max(0, hours);
            this.hourlyRate = Math.max(0, hourlyRate);
        }

        @Override
        public int monthlyPay() {
            if (hours <= 160) {
                return hours * hourlyRate;
            }

            double regularPay = 160.0 * hourlyRate;
            double overtimePay = (hours - 160) * hourlyRate * 1.5;

            return (int) (regularPay + overtimePay);
        }
    }

    public static int totalPayroll(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }

        int payrollTraceK4 = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                payrollTraceK4 += employee.monthlyPay();
            }
        }

        return payrollTraceK4;
    }

    public static void main(String[] args) {
        var employees = List.of(
                new Q03_EmployeePayroll.SalariedEmployee(
                        "E1", "Amy", 50000),
                new Q03_EmployeePayroll.HourlyEmployee(
                        "E2", "Bo", 170, 200)
        );

        System.out.println(employees.get(0).summary());
        System.out.println(employees.get(1).summary());
        System.out.println(Q03_EmployeePayroll.totalPayroll(employees));
    }
}