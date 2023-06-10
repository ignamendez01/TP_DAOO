package model;

import java.time.LocalDate;
import java.util.List;

public class Payroll {

    private List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public float calculate(LocalDate date) {
        float total = 0.0F;
        for (Employee employee: employees) {
            total = total + employee.getContract().calculate(date);
        }
        return total;
    }
}
