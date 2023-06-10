package model;

import java.time.LocalDate;

public abstract class Contract {

    private LocalDate startDate;
    private LocalDate finishDate;
    private float payPerHour;

    public Contract(LocalDate startDate, LocalDate finishDate, float payPerHour) {
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

    public float getPayPerHour() {
        return payPerHour;
    }

    public abstract float calculate(LocalDate date);
}
