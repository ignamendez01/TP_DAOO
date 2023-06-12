package controller;

import model.dto.EmployeeReportDto;
import model.entities.employee.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeController {

    void addEmployee(Employee employee);
    void editEmployee(Employee employee, long id);
    double calculatePayroll(LocalDate localDate);
    Employee undo(long employeeId);
    Employee redo(long employeeId);
    Employee getEmployee(String employeeName);
    void deleteEmployee(long employeeId);
    void printEmployees();
    List<EmployeeReportDto> generatePayrollReport(LocalDate localDate);
}
