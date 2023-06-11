package app;

import model.Employee;
import model.FullTimeContract;
import model.Versionable;

import java.time.LocalDate;
import java.util.List;

public class InitialDatabase {

    public InitialDatabase() {

    }

    public List<Versionable<Employee>> getEmployeeList() {
        return List.of(new Versionable<>(new Employee(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023,7, 15), 10))),
                new Versionable<>(new Employee(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10))));
    }
}
