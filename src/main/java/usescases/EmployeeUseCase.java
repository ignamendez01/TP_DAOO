package usescases;

import model.Employee;
import model.Versionable;

import java.util.List;

public interface EmployeeUseCase {

    void addEmployee(Employee employee);
    List<Versionable<Employee>> getEmployees();
    void editEmployee(Employee employee, long id);
}