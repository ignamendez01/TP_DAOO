package repository;

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
            if (database.get(i).getActual().getID() == id){
                database.remove(database.get(i));
                break;
            }
        }
    }

    public void editEmployeeById(Employee newVersion, long id) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getID() == id) {
                employeeVersionable.update(newVersion);
                break;
            }
        }
    }

    public List<Versionable<Employee>> getEmployees() {
        return database;
    }

    public void print() {
        for (Versionable<Employee> e : database) {
            System.out.println(e.getActual().print());
        }
    }

    public Employee getEmployeeById(long id) {
        Employee e = null;
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getID() == id) {
                e = employeeVersionable.getActual();
                break;
            }
        }
        if (e == null){
            System.out.println("Employee not found");
            return null;
        }else {
            return e;
        }
    }

    @Override
    public Versionable<Employee> getEmployeeVersionableById(long id) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getID() == id) {
                return employeeVersionable;
            }
        }
        return null;
    }


}
