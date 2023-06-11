package usescases;

import model.Employee;
import model.Versionable;
import repository.EmployeeDatabase;

import java.time.LocalDate;
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

    @Override
    public double calculatePayroll(LocalDate localDate) {
        double payRoll = 0.0;
        List<Versionable<Employee>> employees = employeeDatabase.getEmployees();
        for (Versionable<Employee> employee: employees) {
            payRoll = payRoll + employee.getActual().getContracts().getActual().calculate(localDate);
        }
        return payRoll;
    }

}
