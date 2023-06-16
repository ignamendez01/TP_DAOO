package controller;

import model.dto.ContractDto;
import model.dto.EmployeeDto;
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
    public void addEmployee(EmployeeDto employeeDto, ContractDto contractDto) {
        employeeUseCase.addEmployee(employeeDto, contractDto);
    }

    @Override
    public void editEmployee(Employee employee, long id) {
        employeeUseCase.editEmployee(employee, id);
    }

    @Override
    public double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        return employeeUseCase.calculatePayroll(startPeriodDate, endPeriodDate);
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
    public List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        return employeeUseCase.generatePayrollReport(startPeriodDate, endPeriodDate);
    }

}
