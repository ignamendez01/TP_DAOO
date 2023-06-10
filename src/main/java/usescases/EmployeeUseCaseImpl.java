package usescases;

import model.Employee;
import model.Versionable;
import repository.EmployeeDatabase;

import java.util.List;

public class EmployeeUseCaseImpl implements EmployeeUseCase{

    EmployeeDatabase employeeDatabase;

    public EmployeeUseCaseImpl(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDatabase.addEmployee(employee);
    }

    @Override
    public List<Versionable<Employee>> getEmployees() {
        return employeeDatabase.getEmployees();
    }

    @Override
    public void editEmployee(Employee employee, long id) {
        employeeDatabase.editEmployee(employee, id);
    }

}
