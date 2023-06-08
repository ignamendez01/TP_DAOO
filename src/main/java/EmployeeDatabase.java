import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDatabase {
    private List<Employee> database = new ArrayList<>();

    public void addEmployee(String name, String contact, Contract contract) {
        database.add(new Employee(name, contact, contract));
    }

    public void removeEmployee(long id) {
        getEmployeeById(id).ifPresent(database::remove);
    }

    public void editEmployee(long id, String name, String contact, Contract contract) {
        getEmployeeById(id).ifPresent(employee -> {
            employee.setName(name);
            employee.setContact(contact);
            employee.setContract(contract);
        });
    }

    public List<Employee> getDatabase() {
        return database;
    }

    public void print() {
        for (Employee e : database) {
            System.out.println(e);
        }
    }

    private Optional<Employee> getEmployeeById(long id) {
        return database.stream().filter(e -> e.getID() == id).findFirst();
    }
}
