import model.DataProvider;
import model.Employee;
import model.FullTimeContract;
import model.TotalHourContract;

import java.time.LocalDate;
import java.util.List;

public class TestProvider implements DataProvider {
    @Override
    public List<Employee> getEmployeeList() {
        return List.of(new Employee(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023,7, 15), 10)),
                new Employee(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10)));
    }

    public List<Employee> testCase3() {
        return List.of(new Employee(new TotalHourContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10, 10)));
    }
}
