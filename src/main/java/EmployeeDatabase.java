import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDatabase {
    private List<Versionable<Employee>> database = new ArrayList<>();

    public void addEmployee(Employee e) {
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

    public void editEmployee(Employee newVersion) {
        for (Versionable<Employee> employeeVersionable : database) {
            if (employeeVersionable.getActual().getID() == newVersion.getID()) {
                employeeVersionable.update(newVersion);
                break;
            }
        }
    }

    public List<Versionable<Employee>> getDatabase() {
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
}
