package usescases;

import model.dto.*;
import model.entities.contract.Contract;
import model.entities.contract.FullTimeContract;
import model.entities.contract.TotalHourContract;
import model.entities.employee.Employee;
import model.Versionable;
import repository.EmployeeDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeUseCaseImpl implements EmployeeUseCase{

    EmployeeDatabase employeeDatabase;

    public EmployeeUseCaseImpl(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    @Override
    public void addEmployee(EmployeeDto employeeDto, ContractDto contractDto) {
        Contract employeeContract;
        if (contractDto instanceof TotalHourContractDto) { //TODO: Remove instanceof
            employeeContract = new TotalHourContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour(), ((TotalHourContractDto) contractDto).getTotalHours());
        }
        else { //FullContractDTO
            employeeContract = new FullTimeContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour());
        }
        Employee newEmployee = new Employee(employeeDto.getName(), employeeDto.getPhoneNumber(), employeeContract);
        employeeDatabase.addEmployee(newEmployee);
    }

    @Override
    public void printEmployees() {
        for (int i = 0; i < employeeDatabase.getEmployees().size(); i++) {
            System.out.println((i+1)+") "+employeeDatabase.getEmployees().get(i).getActual().getName());
        }
    }

    @Override
    public void editEmployeeProfile(UpdateProfileDto updateProfileDto) {
        Employee oldEmployee = employeeDatabase.getEmployeeById(updateProfileDto.getId());
        Employee newEmployee = new Employee(updateProfileDto.getId(), updateProfileDto.getFullName(), updateProfileDto.getPhoneNumber(), oldEmployee.getContract());
        employeeDatabase.editEmployeeProfileById(updateProfileDto.getId(), newEmployee);
    }

    @Override
    public void editEmployeeContract(UpdateContractDto updateContractDto) {
        ContractDto contractDto = updateContractDto.getContractDto();
        Contract newContract = null;
        if (contractDto instanceof FullTimeContractDto) {
            newContract = new FullTimeContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour());
        }
        else if (contractDto instanceof TotalHourContractDto) {
            newContract = new TotalHourContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour(), ((TotalHourContractDto) contractDto).getTotalHours());
        }
        employeeDatabase.editEmployeeContractById(updateContractDto.getId(), newContract);
    }

    @Override
    public double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        double payRoll = 0.0;
        List<Versionable<Employee>> employees = employeeDatabase.getEmployees();
        for (Versionable<Employee> employee: employees) {
            payRoll = payRoll + employee.getActual().getContract().getActual().calculate(startPeriodDate, endPeriodDate);
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
        Contract actualContract = employee.getActualContract();
        ContractDto contractDto = null;
        if (actualContract instanceof FullTimeContract) {
            contractDto = new FullTimeContractDto(actualContract.getStartDate(), actualContract.getFinishDate(), actualContract.getPayPerHour());
        }
        else if (actualContract instanceof TotalHourContract) {
            contractDto = new TotalHourContractDto(actualContract.getStartDate(), actualContract.getFinishDate(), actualContract.getPayPerHour(), ((TotalHourContract) actualContract).getTotalHours());
        }
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
            double payroll = employee.getActual().getContract().getActual().calculate(startPeriodDate, endPeriodDate);
            ArrayList<String> changes = analyzeChanges(employee);
            report.add(new EmployeeReportDto(employee.getActual().getName(), payroll, changes));
        }
        return report;
    }

    private ArrayList<String> analyzeChanges(Versionable<Employee> versionableEmployee) {
        ArrayList<String> changes = new ArrayList<>();
        Employee lastVersion = versionableEmployee.getActual();
        ArrayList<Employee> history = (ArrayList<Employee>) ((ArrayList<Employee>) versionableEmployee.getHistory()).clone();
        Collections.reverse(history);
        for (Employee employee: history) {
            if (lastVersion.equals(employee)) continue;
            if (!lastVersion.getName().equals(employee.getName())) {
                changes.add("Name: " + employee.getName() + " ---> " + lastVersion.getName());
            }
            if (!lastVersion.getPhoneNumber().equals(employee.getPhoneNumber())) {
                changes.add("PhoneNumber: " + employee.getPhoneNumber() + " ---> " + lastVersion.getPhoneNumber());
            }
            if (!lastVersion.getActualContract().equals(employee.getActualContract())) {
                changes.add("Contract Changes.");
            }
            lastVersion = employee;
        }
        if (changes.size() == 0) {
            changes.add("No Changes.");
        }
        return changes;
    }

}
