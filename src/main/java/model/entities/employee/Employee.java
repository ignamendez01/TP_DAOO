package model.entities.employee;

import model.Versionable;
import model.entities.contract.Contract;

public class Employee {

    private long id;
    String name;
    String phoneNumber;
    Versionable<Contract> contract;

    public Employee(String name, String phoneNumber, Contract contract) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contract = new Versionable<>(contract);
    }

    public Employee(long id, String name, String phoneNumber, Versionable<Contract> contract) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contract = contract;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contract getActualContract() {
        return contract.getActual();
    }

    public Employee setName(String name) {
        return new Employee(this.id, name, this.phoneNumber, this.contract);
    }

    public Employee setPhoneNumber(String phoneNumber) {
        return new Employee(this.id, this.name, phoneNumber, this.contract);
    }

    public Employee setContract(Contract contract) {
        this.contract.update(contract);
        return new Employee(this.id, name, this.phoneNumber, this.contract);
    }

    public Versionable<Contract> getContract() {
        return contract;
    }

    public void setID(long ID) {
        this.id = ID;
    }
}

