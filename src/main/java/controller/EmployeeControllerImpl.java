package controller;

import model.Employee;
import model.Versionable;
import usescases.EmployeeUseCase;

import java.util.List;

public class EmployeeControllerImpl implements EmployeeController{

    EmployeeUseCase employeeUseCase;

    public EmployeeControllerImpl(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeUseCase.addEmployee(employee);
    }

    @Override
    public List<Versionable<Employee>> getEmployee() {
        return employeeUseCase.getEmployees();
    }

    @Override
    public void editEmployee(Employee employee, long id) {
        employeeUseCase.editEmployee(employee, id);
    }

}
