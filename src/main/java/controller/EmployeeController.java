package controller;

import model.dto.ContractDto;
import model.dto.EmployeeDto;
import model.dto.EmployeeReportDto;
import model.entities.employee.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeController {

    void addEmployee(EmployeeDto employeeDto, ContractDto contractDto);
    void editEmployee(Employee employee, long id);
    double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate);
    Employee undo(long employeeId);
    Employee redo(long employeeId);
    Employee getEmployee(String employeeName);
    void deleteEmployee(long employeeId);
    void printEmployees();
    List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate);
}
