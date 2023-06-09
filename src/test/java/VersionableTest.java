import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionableTest {

    //Employee versionable

    @Test
    public void undoTest(){
        Employee e1 = new Employee("Marcos", "123456789", new FullTimeContract(new Date(),
                new Date(2023, Calendar.JULY,14), 14));
        Versionable<Employee> versionable = new Versionable<>(e1);
        Employee e2 = e1.setName("Marco Antonio");
        Employee e3 = e2.setContact("987654321");
        versionable.update(e2);
        versionable.update(e3);
        versionable.undo();
        versionable.undo();
        assertEquals(e1, versionable.getActual());
    }

    @Test
    public void redoTest(){
        Employee e1 = new Employee("Marco", "123456789", new FullTimeContract(new Date(),
                new Date(2023, Calendar.JUNE,14), 14));
        Versionable<Employee> versionable = new Versionable<>(e1);
        Employee e2 = e1.setName("Marco Antonio");
        Employee e3 = e2.setContact("987654321");
        versionable.update(e2);
        versionable.update(e3);
        versionable.undo();
        versionable.undo();
        versionable.redo();
        versionable.redo();
        assertEquals(e3, versionable.getActual());
    }

    @Test
    public void updateInTheMiddleTest(){
        Employee e1 = new Employee("Marcos", "123456789", new FullTimeContract(new Date(),
                new Date(2023, Calendar.JUNE,14), 14));
        Versionable<Employee> versionable = new Versionable<>(e1);
        Employee e2 = e1.setName("Marco Antonio");
        Employee e3 = e2.setContact("987654321");
        Employee e4 = e3.setContact("010101010");
        Employee e5 = e4.setName("Esteban Cortez");
        versionable.update(e2);
        versionable.update(e3);
        versionable.update(e4);
        versionable.undo();
        versionable.undo();
        versionable.undo();
        assertEquals(4, versionable.history.size());
        versionable.update(e5);
        assertEquals(2, versionable.history.size());
    }

    //Contract Versionable

    @Test
    public void contactsVersionsTest(){
        Employee e1 = new Employee("Marcos", "123456789", new FullTimeContract(new Date(2023, Calendar.JUNE,9),
                new Date(2023, Calendar.JUNE,14), 14));

        assertEquals(336,e1.getContract().calculate(new Date(2023, Calendar.JUNE,12)));

        e1.contracts.update(new TotalHourContract(e1.getContract().getStartDate(),
                e1.getContract().getFinishDate(),e1.getContract().getPayPerHour(),5));

        assertEquals(42,(int) e1.getContract().calculate(new Date(2023, Calendar.JUNE,12)));
    }
}
