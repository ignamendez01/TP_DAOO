package model.entities.inspector;

import model.entities.employee.Employee;

public class NameInspector implements Inspector {
    @Override
    public boolean check(Employee currentEmployee, Employee prevEmployee) {
        return !currentEmployee.getName().equals(prevEmployee.getName());
    }

    @Override
    public Change apply(Employee currentEmployee, Employee prevEmployee) {
        return new Change("Name", currentEmployee.getName(), prevEmployee.getName());
    }
}
