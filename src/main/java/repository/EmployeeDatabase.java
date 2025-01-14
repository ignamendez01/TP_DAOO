package repository;

import model.entities.employee.Employee;
import model.Versionable;


import java.util.List;

public interface EmployeeDatabase {

    void addEmployee(Employee e);
    void removeEmployee(long id);
    void editEmployee(long id, Employee newVersion);
    List<Versionable<Employee>> getEmployees();
    Employee getEmployeeById(long id);
    Employee getEmployeeByName(String name);
    Versionable<Employee> getEmployeeVersionableById(long id);

}
