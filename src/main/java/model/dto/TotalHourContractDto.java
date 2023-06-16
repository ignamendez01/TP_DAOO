package model.dto;


import java.time.LocalDate;

public class TotalHourContractDto extends ContractDto {

    private final double totalHours;

    public TotalHourContractDto(LocalDate startDate, LocalDate finishDate, double payPerHour, double totalHours) {
        super(startDate, finishDate, payPerHour);
        this.totalHours = totalHours;
    }

    public double getTotalHours() {
        return totalHours;
    }
}
