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
        if (contractDto instanceof TotalHourContractDto) {
            employeeContract = new TotalHourContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour(), ((TotalHourContractDto) contractDto).getTotalHours());
        }
        else { //FullContractDTO
            employeeContract = new FullTimeContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour());
        }
        Employee newEmployee = new Employee(employeeDto.name(), employeeDto.phoneNumber(), employeeContract);
        employeeDatabase.addEmployee(newEmployee);
    }

    @Override
    public void printEmployees() {
        for (int i = 0; i < employeeDatabase.getEmployees().size(); i++) {
            System.out.println((i+1)+") "+employeeDatabase.getEmployees().get(i).getActual().getName());
        }
    }

    @Override
    public void editEmployee(Employee employee, long id) {
        employeeDatabase.editEmployeeById(employee, id);
    }

    @Override
    public double calculatePayroll(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        double payRoll = 0.0;
        List<Versionable<Employee>> employees = employeeDatabase.getEmployees();
        for (Versionable<Employee> employee: employees) {
            try{
                payRoll = payRoll + employee.getActual().getContract().getActual().calculate(startPeriodDate, endPeriodDate);
            }catch (Exception e){
                System.out.println(e.getMessage() +" for the payroll calculation of "+ employee.getActual().getName());
            }
        }
        return payRoll;
    }

    @Override
    public Employee undo(long employeeId) {
        Versionable<Employee> versionableEmployee = employeeDatabase.getEmployeeVersionableById(employeeId);
        if (versionableEmployee != null) {
            try {
                versionableEmployee.undo();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Employee does not exist with id: " + employeeId);
        }
        assert versionableEmployee != null;
        return versionableEmployee.getActual();
    }

    @Override
    public Employee redo(long employeeId) {
        Versionable<Employee> versionableEmployee = employeeDatabase.getEmployeeVersionableById(employeeId);
        if (versionableEmployee != null) {
            try {
                versionableEmployee.redo();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Employee does not exist with id: " + employeeId);
        }
        assert versionableEmployee != null;
        return versionableEmployee.getActual();
    }

    @Override
    public Employee getEmployee(String employeeName) {
        return employeeDatabase.getEmployeeByName(employeeName);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeDatabase.removeEmployee(employeeId);
    }

    @Override
    public List<EmployeeReportDto> generatePayrollReport(LocalDate startPeriodDate, LocalDate endPeriodDate) {
        ArrayList<EmployeeReportDto> report = new ArrayList<>();
        for (Versionable<Employee> employee : employeeDatabase.getEmployees()) {
            try{
                double payroll = employee.getActual().getContract().getActual().calculate(startPeriodDate, endPeriodDate);
                ArrayList<String> changes = analyzeChanges(employee);
                report.add(new EmployeeReportDto(employee.getActual().getName(), payroll, changes));
            }catch (Exception e){
                System.out.println(e.getMessage() +" for the payroll calculation of "+ employee.getActual().getName());
            }
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
