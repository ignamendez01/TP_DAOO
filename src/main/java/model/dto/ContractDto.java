package model.dto;

import java.time.LocalDate;

public abstract class ContractDto {

    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final double payPerHour;

    public ContractDto(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.payPerHour = payPerHour;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public double getPayPerHour() {
        return payPerHour;
    }
}
