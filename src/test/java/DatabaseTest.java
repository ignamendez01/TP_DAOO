import model.entities.contract.FullTimeContract;
import model.entities.employee.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

import repository.EmployeeDatabase;
import repository.EmployeeDatabaseImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest{

    @Test
    public void findEmployeeTest(){
        EmployeeDatabase database = new EmployeeDatabaseImpl();

        Employee e1 = new Employee("Marco Antonio", "123456789", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,14), 14));
        Employee e2 = new Employee("Carlos Villagran", "987654321", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,19), 18));
        Employee e3 = new Employee("Chris Pine", "010101010", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,11), 7));

        database.addEmployee(e1);
        database.addEmployee(e2);
        database.addEmployee(e3);

        assertEquals("Carlos Villagran", database.getEmployeeById(2).getName());
    }

    @Test
    public void editEmployeeTest(){
        EmployeeDatabase database = new EmployeeDatabaseImpl();
        Employee e1 = new Employee("Marco Antonio", "123456789", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,14), 14));
        Employee e2 = new Employee("Carlos Villagran", "987654321", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,19), 18));
        Employee e3 = new Employee("Chris Pine", "010101010", new FullTimeContract(LocalDate.of(2023, Calendar.JUNE,9),
                LocalDate.of(2023, Calendar.JUNE,11), 7));

        database.addEmployee(e1);
        database.addEmployee(e2);
        database.addEmployee(e3);

        assertEquals("987654321", database.getEmployeeById(2).getPhoneNumber());

        Employee e4 = e2.setPhoneNumber("111111111");

        database.editEmployeeById(e4, e4.getID());

        assertEquals("111111111",database.getEmployeeById(2).getPhoneNumber());
    }
}