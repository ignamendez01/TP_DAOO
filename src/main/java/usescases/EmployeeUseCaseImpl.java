package usescases;

import model.entities.employee.Employee;
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
    public void printEmployees() {
        employeeDatabase.print();
    }

    @Override
    public void editEmployee(Employee employee, long id) {
        employeeDatabase.editEmployeeById(employee, id);
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

    @Override
    public Employee undo(long employeeId) {
        Versionable<Employee> versionableEmployee = employeeDatabase.getEmployeeVersionableById(employeeId);
        if (versionableEmployee != null) {
            versionableEmployee.undo();
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
            versionableEmployee.redo();
        }
        else {
            System.out.println("Employee does not exist with id: " + employeeId);
        }
        assert versionableEmployee != null;
        return versionableEmployee.getActual();
    }

    @Override
    public Employee getEmployee(String employeeName) {
        for (Versionable<Employee> employeeVersionable : employeeDatabase.getEmployees()) {
            if (employeeVersionable.getActual().getName().equalsIgnoreCase(employeeName)) {
                return employeeVersionable.getActual();
            }
        }
        return null;
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeDatabase.removeEmployee(employeeId);
    }

}
