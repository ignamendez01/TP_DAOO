package model.entities.contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Contract {

    private LocalDate startDate;
    private LocalDate finishDate;
    private double payPerHour;

    public Contract(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        this.startDate = startDate;
        if (finishDate.isAfter(startDate)){
            this.finishDate = finishDate;
            this.payPerHour = payPerHour;
        }else{
            throw new RuntimeException("Invalid Date");
        }

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

    public double calculate(LocalDate startPeriodDate, LocalDate endPeriodDate){
        if (endPeriodDate.isAfter(startPeriodDate)) {
            if (endPeriodDate.isAfter(startDate)  && startPeriodDate.isBefore(finishDate)) {
                if (startPeriodDate.isBefore(startDate)){
                    if (endPeriodDate.isAfter(finishDate)) {
                        return calculatePeriod(startDate, finishDate);
                    } else {
                        return calculatePeriod(startDate, endPeriodDate);
                    }
                }else {
                    if (endPeriodDate.isAfter(finishDate)) {
                        return calculatePeriod(startPeriodDate, finishDate);
                    } else {
                        return calculatePeriod(startPeriodDate, endPeriodDate);
                    }
                }
            } else {
                throw new RuntimeException("Invalid period");
            }
        }else{
            throw new RuntimeException("Invalid date");
        }
    }

    public abstract double calculatePeriod(LocalDate startDate, LocalDate endPeriodDate);

    public abstract void print();
}
