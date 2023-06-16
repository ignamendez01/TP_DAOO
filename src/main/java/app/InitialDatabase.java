package app;

import model.entities.contract.Contract;
import model.entities.employee.Employee;
import model.entities.contract.FullTimeContract;
import model.Versionable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitialDatabase {

    public InitialDatabase() {

    }

    public List<Versionable<Employee>> getEmployeeList() {
        List<Versionable<Employee>> list = new ArrayList<>();
        list.add(new Versionable<>(new Employee(1, "Maria Fernandez", "1111111111", new Versionable<>(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023,7, 15), 10)))));
        list.add(new Versionable<>(new Employee(2, "Alejandro Lopez", "2222222222", new Versionable<>(new FullTimeContract(LocalDate.of(2023, 7, 5), LocalDate.of(2023, 7, 10), 10)))));
        return list;
    }
}
