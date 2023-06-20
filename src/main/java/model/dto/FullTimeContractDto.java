package model.dto;

import java.time.LocalDate;

public class FullTimeContractDto extends ContractDto {

    private final double hoursPerPay;

    public FullTimeContractDto(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        super(startDate, finishDate, payPerHour);
        this.hoursPerPay = 8.0;
    }

    public double getHoursPerPay() {
        return hoursPerPay;
    }
}
