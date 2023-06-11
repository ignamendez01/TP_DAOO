package model;

import model.entities.employee.Employee;

import java.util.List;

public interface DataProvider {

    List<Employee> getEmployeeList();
}