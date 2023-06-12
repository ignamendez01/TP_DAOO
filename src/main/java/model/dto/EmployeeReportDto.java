package model.dto;

import java.util.List;

public class EmployeeReportDto {

    String name;
    double payroll;
    List<String> changes;

    public EmployeeReportDto(String name, double payroll, List<String> changes) {
        this.name = name;
        this.payroll = payroll;
        this.changes = changes;
    }

    public String getName() {
        return name;
    }

    public double getPayroll() {
        return payroll;
    }

    public List<String> getChanges() {
        return changes;
    }
}
