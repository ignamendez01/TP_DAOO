import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestProvider implements DataProvider {
    @Override
    public List<Employee> getEmployeeList() {
        return List.of(new Employee(new FullTimeContract(new Date(2023, Calendar.JULY, 5), new Date(2023, Calendar.JULY, 15), 10)),
                new Employee(new FullTimeContract(new Date(2023, Calendar.JULY, 5), new Date(2023, Calendar.JULY, 10), 10)));
    }

    public List<Employee> testCase3() {
        return List.of(new Employee(new TotalHourContract(new Date(2023, Calendar.JULY, 5), new Date(2023, Calendar.JULY, 10), 10, 10)));
    }
}
