package model.dto;

import java.time.LocalDate;

public class FullTimeContractDto extends ContractDto {

    public FullTimeContractDto(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        super(startDate, finishDate, payPerHour);
    }
}
