package controller;

import model.Employee;
import model.Versionable;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeController {

    void addEmployee(Employee employee);
    List<Versionable<Employee>> getEmployee();
    void editEmployee(Employee employee, long id);
    double calculatePayroll(LocalDate localDate);
}
