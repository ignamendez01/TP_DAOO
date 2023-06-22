package model.dto;

import model.entities.inspector.Change;

import java.util.List;

public class EmployeeReportDto {

    String name;
    double payroll;
    List<Change> changes;

    public EmployeeReportDto(String name, double payroll, List<Change> changes) {
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

    public List<Change> getChanges() {
        return changes;
    }
}
