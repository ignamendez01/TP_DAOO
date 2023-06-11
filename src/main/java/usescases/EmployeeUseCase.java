package usescases;

import model.entities.employee.Employee;

import java.time.LocalDate;

public interface EmployeeUseCase {

    void addEmployee(Employee employee);
    void printEmployees();
    void editEmployee(Employee employee, long id);
    double calculatePayroll(LocalDate localDate);
    Employee undo(long employeeId);
    Employee redo(long employeeId);
    Employee getEmployee(String employeeName);
    void deleteEmployee(long employeeId);
}
