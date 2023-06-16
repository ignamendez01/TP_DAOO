package repository;

import model.entities.contract.Contract;
import model.entities.employee.Employee;
import model.Versionable;


import java.util.List;

public interface EmployeeDatabase {

    void addEmployee(Employee e);
    void removeEmployee(long id);
    void editEmployeeProfileById(long id, Employee newVersion);
    void editEmployeeContractById(long id, Contract newContract);
    List<Versionable<Employee>> getEmployees();
    Employee getEmployeeById(long id);
    Employee getEmployeeByName(String name);
    Versionable<Employee> getEmployeeVersionableById(long id);

}
