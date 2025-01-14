import model.DataProvider;
import model.entities.employee.Employee;
import model.entities.contract.FullTimeContract;
import model.entities.contract.TotalHourContract;

import java.time.LocalDate;
import java.util.List;

public class TestProvider implements DataProvider {
    @Override
    public List<Employee> getEmployeeList() {
        return List.of(new Employee("Employee1", "1111111111", new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023,7, 15), 10)),
                new Employee("Employee2", "2222222222", new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10)));
    }

    public List<Employee> testCase3() {
        return List.of(new Employee("Employee1", "1111111111", new TotalHourContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10, 10)));
    }
}
