import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest{

    @Test
    public void FindEmployeeTest(){
        EmployeeDatabase database = new EmployeeDatabase();

        Employee e1 = new Employee("Marco Antonio", "123456789", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,14), 14));
        Employee e2 = new Employee("Carlos Villagran", "987654321", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,19), 18));
        Employee e3 = new Employee("Chris Pine", "010101010", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,11), 7));

        database.addEmployee(e1);
        database.addEmployee(e2);
        database.addEmployee(e3);

        assertEquals("Carlos Villagran",database.getEmployeeById(2).getName());
    }

    @Test
    public void editEmployeeTest(){
        EmployeeDatabase database = new EmployeeDatabase();
        Employee e1 = new Employee("Marco Antonio", "123456789", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,14), 14));
        Employee e2 = new Employee("Carlos Villagran", "987654321", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,19), 18));
        Employee e3 = new Employee("Chris Pine", "010101010", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,11), 7));

        database.addEmployee(e1);
        database.addEmployee(e2);
        database.addEmployee(e3);

        assertEquals("987654321",database.getEmployeeById(2).getContact());

        Employee e4 = e2.setContact("111111111");

        database.editEmployee(e4);

        assertEquals("111111111",database.getEmployeeById(2).getContact());
    }
}