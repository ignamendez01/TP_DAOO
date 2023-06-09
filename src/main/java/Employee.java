public class Employee {
    private long ID;
    String name;
    String contact;
    Versionable<Contract> contracts;
    private static long ids = 0;

    public Employee(Contract contract) {
        this.contracts = new Versionable<>(contract);
    }

    public Employee(String name, String contact, Contract contract) {
        this.ID = ++Employee.ids;
        this.name = name;
        this.contact = contact;
        this.contracts = new Versionable<>(contract);
    }

    public Employee(long id, String name, String contact, Contract contract) {
        this.ID = id;
        this.name = name;
        this.contact = contact;
        this.contracts = new Versionable<>(contract);
    }

    public Employee(long id, String name, String contact, Versionable<Contract> contracts) {
        this.ID = id;
        this.name = name;
        this.contact = contact;
        this.contracts = contracts;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public Contract getContract() {
        return contracts.getActual();
    }

    public Employee setName(String name) {
        return new Employee(this.ID, name, this.contact, this.contracts);
    }

    public Employee setContact(String contact) {
        return new Employee(this.ID, this.name, contact, this.contracts);
    }

    public Employee setContract(Contract contract) {
        contracts.update(contract);
        return new Employee(this.ID, name, this.contact, this.contracts);
    }

    public String print() {
        return ID+") "+name;
    }
}
