package model.entities.inspector;

import model.Versionable;
import model.entities.employee.Employee;

public interface Inspector {

    boolean check(Employee currentEmployee, Employee prevEmployee);

    Change apply(Employee currentEmployee, Employee prevEmployee);
}
