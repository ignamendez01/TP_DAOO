package repository;

import model.Employee;
import model.Versionable;


import java.util.List;

public interface EmployeeDatabase {

    void addEmployee(Employee e);
    void removeEmployee(long id);
    void editEmployee(Employee newVersion, long id);
    List<Versionable<Employee>> getEmployees();
    void print();
    Employee getEmployeeById(long id);

}
