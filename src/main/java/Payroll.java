import java.util.Date;
import java.util.List;

public class Payroll {

    private List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public float calculate(Date date) {
        float total = 0.0F;
        for (Employee employee: employees) {
            total = total + employee.contract.calculate(date);
        }
        return total;
    }
}
