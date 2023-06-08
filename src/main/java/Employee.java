public class Employee {
    private long ID;
    String name;
    String contact;
    Contract contract;
    private static long ids = 0;

    public Employee(Contract contract) {
        this.contract = contract;
    }

    public Employee(String name, String contact, Contract contract) {
        this.ID = Employee.ids++;
        this.name = name;
        this.contact = contact;
        this.contract = contract;
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
        return contract;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
