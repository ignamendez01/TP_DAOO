package model.dto;

public class EmployeeDto {

    private long id;
    private ContractDto contractDto;
    private final String name;
    private final String phoneNumber;


    public EmployeeDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public EmployeeDto(long id, String name, String phoneNumber, ContractDto contractDto) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        //this.contractDto = contractDto;
    }

    public long getId() {
        return id;
    }

    public ContractDto getContractDto() {
        return contractDto;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
