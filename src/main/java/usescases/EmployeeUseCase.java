package usescases;

import model.dto.EmployeeReportDto;
import model.entities.employee.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeUseCase {

    void addEmployee(Employee employee);
    void printEmployees();
    void editEmployee(Employee employee, long id);
    double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate);
    Employee undo(long employeeId);
    Employee redo(long employeeId);
    Employee getEmployee(String employeeName);
    void deleteEmployee(long employeeId);
    List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate);
}
