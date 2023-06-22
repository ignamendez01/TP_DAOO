package usescases;

import model.dto.*;
import model.entities.contract.Contract;
import model.entities.contractFactory.ContractFactory;
import model.entities.employee.Employee;
import model.Versionable;
import model.entities.inspector.Change;
import model.entities.inspector.EmployeeInspector;
import model.entities.inspector.Inspector;
import repository.EmployeeDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeUseCaseImpl implements EmployeeUseCase{

    EmployeeDatabase employeeDatabase;

    public EmployeeUseCaseImpl(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto, ContractDto contractDto) {
        Contract employeeContract = ContractFactory.createContract(contractDto);
        Employee newEmployee = new Employee(employeeDto.getName(), employeeDto.getPhoneNumber(), employeeContract);
        employeeDatabase.addEmployee(newEmployee);
    }

    @Override
    public List<EmployeeDto> printEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Versionable<Employee> employee: employeeDatabase.getEmployees()) {
            employeeDtos.add(new EmployeeDto(employee.getActual().getName(), employee.getActual().getPhoneNumber()));
        }
        return employeeDtos;
    }

    @Override
    public void editEmployeeProfile(UpdateProfileDto updateProfileDto) {
        Employee oldEmployee = employeeDatabase.getEmployeeById(updateProfileDto.id());
        Employee newEmployee = new Employee(updateProfileDto.id(), updateProfileDto.fullName(), updateProfileDto.phoneNumber(), oldEmployee.getContract());
        employeeDatabase.editEmployee(updateProfileDto.id(), newEmployee);
    }

    @Override
    public void editEmployeeContract(UpdateContractDto updateContractDto) {
        Employee oldEmployee = employeeDatabase.getEmployeeById(updateContractDto.id());
        ContractDto contractDto = updateContractDto.contractDto();
        Contract newContract = ContractFactory.createContract(contractDto);
        Employee newEmployee = new Employee(updateContractDto.id(), oldEmployee.getName(), oldEmployee.getPhoneNumber(), newContract);
        employeeDatabase.editEmployee(updateContractDto.id(), newEmployee);
    }

    @Override
    public double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        double payRoll = 0.0;
        List<Versionable<Employee>> employees = employeeDatabase.getEmployees();
        for (Versionable<Employee> employee: employees) {
            payRoll = payRoll + employee.getActual().getContract().calculate(startPeriodDate, endPeriodDate);
        }
        return payRoll;
    }

    @Override
    public EmployeeDto undo(long employeeId) {
        Versionable<Employee> versionableEmployee = employeeDatabase.getEmployeeVersionableById(employeeId);
        versionableEmployee.undo();
        Employee newEmployee = versionableEmployee.getActual();
        return new EmployeeDto(newEmployee.getName(), newEmployee.getPhoneNumber());
    }

    @Override
    public EmployeeDto redo(long employeeId) {
        Versionable<Employee> versionableEmployee = employeeDatabase.getEmployeeVersionableById(employeeId);
        versionableEmployee.redo();
        Employee newEmployee = versionableEmployee.getActual();
        return new EmployeeDto(newEmployee.getName(), newEmployee.getPhoneNumber());
    }

    @Override
    public EmployeeDto getEmployee(String employeeName) {
        Employee employee = employeeDatabase.getEmployeeByName(employeeName);
        Contract actualContract = employee.getContract();
        ContractDto contractDto = ContractFactory.createContractDto(actualContract);
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getPhoneNumber(), contractDto);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeDatabase.removeEmployee(employeeId);
    }

    @Override
    public List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        ArrayList<EmployeeReportDto> report = new ArrayList<>();
        for (Versionable<Employee> employee : employeeDatabase.getEmployees()) {
            double payroll = employee.getActual().getContract().calculate(startPeriodDate, endPeriodDate);
            ArrayList<Change> changes = analyzeChanges(employee);
            report.add(new EmployeeReportDto(employee.getActual().getName(), payroll, changes));
        }
        return report;
    }

    private ArrayList<Change> analyzeChanges(Versionable<Employee> versionableEmployee) {
        ArrayList<Change> changes = new ArrayList<>();
        Employee lastVersion = versionableEmployee.getActual();
        ArrayList<Employee> history = new ArrayList<>(versionableEmployee.getHistory());
        Collections.reverse(history);

        EmployeeInspector employeeInspector = new EmployeeInspector();
        for (Employee employee: history) {
            if (lastVersion.equals(employee)) continue;
            for (Inspector inspector: employeeInspector.getInspectors()) {
                if (inspector.check(lastVersion, employee))
                    changes.add(inspector.apply(lastVersion, employee));
            }
        }
        return changes;
    }

}
