package model.entities.employee;

import model.Versionable;
import model.entities.contract.Contract;

public class Employee {
    private long ID;
    String name;
    String phoneNumber;
    Versionable<Contract> contracts;

    public Employee(Contract contract) {
        this.contracts = new Versionable<>(contract);
    }

    public Employee(String name, String phoneNumber, Contract contract) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contracts = new Versionable<>(contract);
    }

    public Employee(long id, String name, String phoneNumber, Contract contract) {
        this.ID = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contracts = new Versionable<>(contract);
    }

    public Employee(long id, String name, String phoneNumber, Versionable<Contract> contracts) {
        this.ID = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contracts = contracts;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contract getContract() {
        return contracts.getActual();
    }

    public Employee setName(String name) {
        return new Employee(this.ID, name, this.phoneNumber, this.contracts);
    }

    public Employee setContact(String contact) {
        return new Employee(this.ID, this.name, contact, this.contracts);
    }

    public Employee setContract(Contract contract) {
        contracts.update(contract);
        return new Employee(this.ID, name, this.phoneNumber, this.contracts);
    }

    public String print() {
        return ID+") "+name;
    }

    public Versionable<Contract> getContracts() {
        return contracts;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}

