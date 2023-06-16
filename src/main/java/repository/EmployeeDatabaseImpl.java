package repository;

import model.entities.contract.Contract;
import model.entities.employee.Employee;
import model.Versionable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseImpl implements EmployeeDatabase{

    long id;
    private final List<Versionable<Employee>> database;

    public EmployeeDatabaseImpl() {
        this.id = 1;
        this.database = new ArrayList<>();
    }

    public EmployeeDatabaseImpl(List<Versionable<Employee>> database) {
        this.id = database.size() + 1;
        this.database = database;
    }

    public void addEmployee(Employee e) {
        e.setID(id++);
        Versionable<Employee> newElement = new Versionable<>(e);
        database.add(newElement);
    }

    public void removeEmployee(long id) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getActual().getId() == id){
                database.remove(database.get(i));
                break;
            }
        }
    }

    public void editEmployeeProfileById(long id, Employee newVersion) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getId() == id) {
                employeeVersionable.update(newVersion);
                break;
            }
        }
    }

    @Override
    public void editEmployeeContractById(long id, Contract newContract) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getId() == id) {
                employeeVersionable.getActual().getContract().update(newContract);
                break;
            }
        }
    }

    public List<Versionable<Employee>> getEmployees() {
        return database;
    }

    public Employee getEmployeeById(long id) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getId() == id) {
                return employeeVersionable.getActual();
            }
        }
        throw new RuntimeException("Employee not found");
    }

    @Override
    public Employee getEmployeeByName(String name) {
        for (Versionable<Employee> employeeVersionable : getEmployees()) {
            if (employeeVersionable.getActual().getName().equalsIgnoreCase(name)) {
                return employeeVersionable.getActual();
            }
        }
        return null;
    }

    @Override
    public Versionable<Employee> getEmployeeVersionableById(long id) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getId() == id) {
                return employeeVersionable;
            }
        }
        throw new RuntimeException("Employee does not exist with id: " + id);
    }


}
