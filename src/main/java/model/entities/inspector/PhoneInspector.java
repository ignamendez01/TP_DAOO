package model.entities.inspector;

import model.entities.employee.Employee;

public class PhoneInspector implements Inspector {
    @Override
    public boolean check(Employee currentEmployee, Employee prevEmployee) {
        return !currentEmployee.getPhoneNumber().equals(prevEmployee.getPhoneNumber());
    }

    @Override
    public Change apply(Employee currentEmployee, Employee prevEmployee) {
        return new Change("PhoneNumber", currentEmployee.getPhoneNumber(), prevEmployee.getPhoneNumber());
    }
}
