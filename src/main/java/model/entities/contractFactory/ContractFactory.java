package model.entities.contractFactory;

import model.dto.ContractDto;
import model.dto.FullTimeContractDto;
import model.dto.TotalHourContractDto;
import model.entities.contract.Contract;
import model.entities.contract.FullTimeContract;
import model.entities.contract.TotalHourContract;

public class  ContractFactory {

    public static Contract createContract(ContractDto contractDto) {
        if (contractDto instanceof FullTimeContractDto) {
            return new FullTimeContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour());
        }
        else if (contractDto instanceof TotalHourContractDto) {
            return new TotalHourContract(contractDto.getStartDate(), contractDto.getFinishDate(), contractDto.getPayPerHour(), ((TotalHourContractDto) contractDto).getTotalHours());
        }
        else {
            throw new IllegalArgumentException("Unsupported contract Dto type");
        }
    }

    public static ContractDto createContractDto(Contract contract) {
        if (contract instanceof FullTimeContract) {
            return new FullTimeContractDto(contract.getStartDate(), contract.getFinishDate(), contract.getPayPerHour());
        }
        else if (contract instanceof TotalHourContract) {
            return new TotalHourContractDto(contract.getStartDate(), contract.getFinishDate(), contract.getPayPerHour(), ((TotalHourContract) contract).getTotalHours());
        }
        else {
            throw new IllegalArgumentException("Unsupported contract type");
        }
    }
}
