package model.entities.inspector;

import model.entities.employee.Employee;

public class ContractInspector implements Inspector {
    @Override
    public boolean check(Employee currentEmployee, Employee prevEmployee) {
        return !currentEmployee.getContract().equals(prevEmployee.getContract());
    }

    @Override
    public Change apply(Employee currentEmployee, Employee prevEmployee) {
        return new Change("Contract", currentEmployee.getContract().toString(), currentEmployee.getContract().toString());
    }
}
