import java.util.List;

public class DefaultProvider implements DataProvider {
    @Override
    public List<Employee> getEmployeeList() {
        return List.of(new Employee(new Contract()));
    }
}
