package usescases;

import model.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeUseCase {

    void addEmployee(EmployeeDto employeeDto, ContractDto contractDto);
    List<EmployeeDto> printEmployees();
    void editEmployeeProfile(UpdateProfileDto updateProfileDto);
    void editEmployeeContract(UpdateContractDto updateContractDto);
    double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate);
    EmployeeDto undo(long employeeId);
    EmployeeDto redo(long employeeId);
    EmployeeDto getEmployee(String employeeName);
    void deleteEmployee(long employeeId);
    List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate);

}
