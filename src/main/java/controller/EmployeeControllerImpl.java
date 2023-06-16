package controller;

import model.dto.*;
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
    public void editEmployeeProfile(UpdateProfileDto updateProfileDto) {
        employeeUseCase.editEmployeeProfile(updateProfileDto);
    }

    @Override
    public void editEmployeeContract(UpdateContractDto updateContractDto) {
        employeeUseCase.editEmployeeContract(updateContractDto);
    }

    @Override
    public double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        return employeeUseCase.calculatePayroll(startPeriodDate, endPeriodDate);
    }

    @Override
    public EmployeeDto undo(long employeeId) {
        return employeeUseCase.undo(employeeId);
    }

    @Override
    public EmployeeDto redo(long employeeId) {
        return employeeUseCase.redo(employeeId);
    }

    @Override
    public EmployeeDto getEmployee(String employeeName) {
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
