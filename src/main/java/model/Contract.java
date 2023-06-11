package model;

import java.time.LocalDate;

public abstract class Contract {

    private LocalDate startDate;
    private LocalDate finishDate;
    private double payPerHour;

    public Contract(LocalDate startDate, LocalDate finishDate, double payPerHour) {
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

    public abstract double calculate(LocalDate date);
}
