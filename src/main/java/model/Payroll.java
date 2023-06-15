package model;

import model.entities.employee.Employee;

import java.time.LocalDate;
import java.util.List;

public class Payroll {

    private List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public double calculate(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        double total = 0.0;
        for (Employee employee: employees) {
            total = total + employee.getContract().calculate(startPeriodDate, endPeriodDate);
        }
        return total;
    }
}
