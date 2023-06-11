package repository;

import model.entities.employee.Employee;
import model.Versionable;


import java.util.List;

public interface EmployeeDatabase {

    void addEmployee(Employee e);
    void removeEmployee(long id);
    void editEmployeeById(Employee newVersion, long id);
    List<Versionable<Employee>> getEmployees();
    void print();
    Employee getEmployeeById(long id);
    Versionable<Employee> getEmployeeVersionableById(long id);

}
