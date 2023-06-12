package controller;

import model.dto.EmployeeReportDto;
import model.entities.employee.Employee;
import usescases.EmployeeUseCase;

import java.time.LocalDate;
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
    public void editEmployee(Employee employee, long id) {
        employeeUseCase.editEmployee(employee, id);
    }

    @Override
    public double calculatePayroll(LocalDate localDate) {
        return employeeUseCase.calculatePayroll(localDate);
    }

    @Override
    public Employee undo(long employeeId) {
        return employeeUseCase.undo(employeeId);
    }

    @Override
    public Employee redo(long employeeId) {
        return employeeUseCase.redo(employeeId);
    }

    @Override
    public Employee getEmployee(String employeeName) {
        return employeeUseCase.getEmployee(employeeName);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeUseCase.deleteEmployee(employeeId);
    }

    @Override
    public void printEmployees() {
        employeeUseCase.printEmployees();
    }

    @Override
    public List<EmployeeReportDto> generatePayrollReport(LocalDate localDate) {
        return employeeUseCase.generatePayrollReport(localDate);
    }

}
