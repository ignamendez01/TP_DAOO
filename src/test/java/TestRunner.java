import model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner {

    @Test
    public void testFullLengthContracts() {
        DataProvider dataProvider = new TestProvider();
        List<Employee> employees = dataProvider.getEmployeeList();
        Payroll payroll = new Payroll(employees);
        assertEquals(1200.0F, payroll.calculate(LocalDate.of(2024, Calendar.JULY, 1)), 0.001);
    }

    @Test
    public void testHalfLengthContracts() {
        TestProvider dataProvider = new TestProvider();
        List<Employee> employees = dataProvider.getEmployeeList();
        Payroll payroll = new Payroll(employees);
        assertEquals(800.0F, payroll.calculate(LocalDate.of(2023, Calendar.JULY, 10)), 0.001);
    }

    @Test
    public void testFullLengthContractsWithTotalHourContract() {
        TestProvider dataProvider = new TestProvider();
        List<Employee> employees = dataProvider.testCase3();
        Payroll payroll = new Payroll(employees);
        assertEquals(100.0F, payroll.calculate(LocalDate.of(2023, Calendar.JULY, 10)), 0.001);
    }

    @Test
    public void testHalfLengthContractsWithTotalHourContract() {
        TestProvider dataProvider = new TestProvider();
        List<Employee> employees = dataProvider.testCase3();
        Payroll payroll = new Payroll(employees);
        assertEquals(40.0F, payroll.calculate(LocalDate.of(2023, Calendar.JULY, 7)), 0.001);
    }
}
