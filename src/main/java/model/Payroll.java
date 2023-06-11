package model;

import java.time.LocalDate;
import java.util.List;

public class Payroll {

    private List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public double calculate(LocalDate date) {
        double total = 0.0;
        for (Employee employee: employees) {
            total = total + employee.getContract().calculate(date);
        }
        return total;
    }
}
