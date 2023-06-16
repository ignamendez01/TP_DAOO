package model.dto;


public class UpdateContractDto {

    private final long id;
    private final ContractDto contractDto;

    public UpdateContractDto(long id, ContractDto contractDto) {
        this.id = id;
        this.contractDto = contractDto;
    }

    public long getId() {
        return id;
    }

    public ContractDto getContractDto() {
        return contractDto;
    }
}
